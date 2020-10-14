package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.component.HandlerExceptionFacebook;
import com.bezkoder.spring.data.cassandra.dto.request.FacebookTokenDTO;
import com.bezkoder.spring.data.cassandra.dto.request.FacebookTokenUpdateDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookTokenResponseDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookTokenWorkerResponse;
import com.bezkoder.spring.data.cassandra.exception.FacebookTokenAlreadyExistException;
import com.bezkoder.spring.data.cassandra.exception.FacebookTokenNotAvailableException;
import com.bezkoder.spring.data.cassandra.exception.FacebookTokenNotFoundException;
import com.bezkoder.spring.data.cassandra.function.Utils;
import com.bezkoder.spring.data.cassandra.mapper.FacebookTokenMapper;
import com.bezkoder.spring.data.cassandra.model.FacebookTokens;
import com.bezkoder.spring.data.cassandra.repository.FacebookTokensRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class FacebookTokensServiceImpl implements FacebookTokensService {

    @Autowired
    private FacebookTokensRepository facebookTokensRepository;

    @Autowired
    private FacebookTokenMapper facebookTokenMapper;

    @Autowired
    private HandlerExceptionFacebook handlerExceptionFacebook;

    @Override
    public List<FacebookTokens> findAllToken() {
        return (List<FacebookTokens>) facebookTokensRepository.findAll();
    }

    @Override
    public FacebookTokens createToken(FacebookTokenDTO facebookTokenDTO) {

        Optional<FacebookTokens> facebookTokens = facebookTokensRepository.findById(facebookTokenDTO.getToken());
        if (facebookTokens.isPresent()) {
            throw new FacebookTokenAlreadyExistException("Token already exist");
        }

        FacebookTokens currentFacebookToken = facebookTokenMapper.toFacebookToken(facebookTokenDTO);

        currentFacebookToken.setCreatedTime(new Date());
        currentFacebookToken.setStatusUse(false);
        currentFacebookToken.setValid(true);

        facebookTokensRepository.save(currentFacebookToken);

        return currentFacebookToken;
    }

    @Override
    public void deleteByChainToken(String chainToken) {
        facebookTokensRepository.deleteById(chainToken);
    }

    @Override
    public FacebookTokens updateTokensByChainToken(String chainToken, FacebookTokenUpdateDTO facebookTokenUpdateDTO) {
        Optional<FacebookTokens> currentFacebookToken = facebookTokensRepository.findById(chainToken);
        if (!currentFacebookToken.isPresent()) {
            throw new FacebookTokenNotFoundException("Token not found");
        }

        currentFacebookToken.get().setValid(facebookTokenUpdateDTO.isValid());
        currentFacebookToken.get().setStatusUse(facebookTokenUpdateDTO.isStatusUse());
        currentFacebookToken.get().setLastTimeUsed(facebookTokenUpdateDTO.getLastTimeUsed());
        currentFacebookToken.get().setCountUse(facebookTokenUpdateDTO.getCountUse());

        facebookTokensRepository.save(currentFacebookToken.get());
        return currentFacebookToken.get();
    }

    @Override
    public FacebookTokens getFacebookTokensByChainToken() {

        List<FacebookTokens> facebookTokensList = facebookTokensRepository.findAllByStatusUseAndAndValid(false, true);
        if (!facebookTokensList.isEmpty()) {
            int countMin = 10000;
            String token = null;
            for (FacebookTokens f : facebookTokensList) {
                if (f.getCountUse() < countMin) {
                    countMin = f.getCountUse();
                    token = f.getToken();
                }
            }
            FacebookTokens currentFacebookToken = facebookTokensRepository.findById(token).orElse(null);
            return currentFacebookToken;
        } else {
            List<FacebookTokens> facebookTokens = facebookTokensRepository.findAllByStatusUseAndAndValid(true, true);
            if (!facebookTokens.isEmpty()) {
                for (FacebookTokens facebookTokens1 : facebookTokens) {
                    if ((new Date().getTime() - facebookTokens1.getLastTimeUsed().getTime()) > 5L) {
                        return facebookTokens1;
                    }
                }
            }
        }
        throw new FacebookTokenNotAvailableException("There are no tokens available");
    }

    @Override
    public Optional<FacebookTokens> findByToken(String token) {
        Optional<FacebookTokens> facebookTokens = facebookTokensRepository.findById(token);
        if (!facebookTokens.isPresent()) {
            throw new FacebookTokenNotFoundException("Token not found");
        }
        return facebookTokens;
    }

    @Override
    public FacebookTokens workerUpdateToken(String token) {

        Optional<FacebookTokens> facebookTokens = facebookTokensRepository.findById(token);
        if (!facebookTokens.isPresent()) {
            throw new FacebookTokenNotFoundException("Token not found");
        }
        int count = facebookTokens.get().getCountUse() + 1;
        facebookTokens.get().setCountUse(count);
        facebookTokens.get().setLastTimeUsed(new Date());
        facebookTokens.get().setStatusUse(false);
        facebookTokensRepository.save(facebookTokens.get());
        return facebookTokens.get();
    }

    @Override
    public FacebookTokens workerResponseError(FacebookTokenWorkerResponse facebookTokenWorkerResponse) {
        Optional<FacebookTokens> facebookTokens = facebookTokensRepository.findById(facebookTokenWorkerResponse.getToken());
        if (!facebookTokens.isPresent()) {
            throw new FacebookTokenNotFoundException("Token not found");
        }

        final FacebookTokens fbToken = facebookTokens.get();
        if (handlerExceptionFacebook.valid(facebookTokenWorkerResponse.getFacebookError())) {
            fbToken.setStatusUse(false);
            fbToken.setValid(false);
        } else {
            int count = fbToken.getCountUse() + 1;
            fbToken.setCountUse(count);
        }
        facebookTokensRepository.save(fbToken);

        return fbToken;
    }

    @Override
    public FacebookTokenResponseDTO executeResponseWorker(FacebookTokenWorkerResponse facebookTokenWorkerResponse) {
        if (facebookTokenWorkerResponse.getFacebookError() == null) {

            FacebookTokens facebookTokens = this.workerUpdateToken(facebookTokenWorkerResponse.getToken());
            FacebookTokenResponseDTO facebookTokenResponseDTO = facebookTokenMapper.toFacebookTokenDTO(facebookTokens);
            return facebookTokenResponseDTO;
        } else {
            FacebookTokens facebookTokens = this.workerResponseError(facebookTokenWorkerResponse);
            FacebookTokenResponseDTO facebookTokenResponseDTO = facebookTokenMapper.toFacebookTokenDTO(facebookTokens);
            return facebookTokenResponseDTO;
        }


    }

    @Override
    public List<FacebookTokens> readFromExcel(MultipartFile multipartFile) {

        List<FacebookTokens> list = new ArrayList<FacebookTokens>();
        try {
            @SuppressWarnings("resource")
            Workbook workbook =new XSSFWorkbook(multipartFile.getInputStream());
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i= 0; i<numberOfSheets;i++){
                Sheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    FacebookTokenDTO facebookTokenDTO = new FacebookTokenDTO();
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        if (cell.getColumnIndex() == 0) {
                            facebookTokenDTO.setToken(cell.getStringCellValue());
                            if (facebookTokenDTO.getToken().equalsIgnoreCase("")) {
                                break;
                            } else {

                                FacebookTokens facebookTokens = this.createToken(facebookTokenDTO);
                                list.add(facebookTokens);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
