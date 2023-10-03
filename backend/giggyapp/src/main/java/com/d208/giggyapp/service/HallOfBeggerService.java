package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.begger.BeggerNeighborDto;
import com.d208.giggyapp.dto.begger.BeggerNeighborResultDto;
import com.d208.giggyapp.dto.begger.BeggerRankDto;
import com.d208.giggyapp.dto.begger.TopBeggerRankDto;
import com.d208.giggyapp.repository.HallOfBeggerRepository;
import com.d208.giggyapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class HallOfBeggerService {

    private final HallOfBeggerRepository hallOfBeggerRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> getTopHallOfBegger() {
//        String url = "https://j9d208.p.ssafy.io:8282/api/v1/rank/hall-of-begger";
        String url = "http://127.0.0.1:8083/api/v1/rank/hall-of-begger";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<BeggerRankDto>> response =
                    restTemplate.exchange(
                            uri.toString(),
                            HttpMethod.GET,
                            requestEntity,
                            new ParameterizedTypeReference<List<BeggerRankDto>>() {}
                    );
            List<BeggerRankDto> beggerRankDtos = response.getBody();
            List<TopBeggerRankDto> topBeggerRankDtos = new ArrayList<>();
            for (BeggerRankDto beggerRankDto : beggerRankDtos){
                User user = userRepository.findById(beggerRankDto.getUserId()).orElseThrow(() ->
                        new NoSuchElementException("존재하지 않는 유저입니다."));
                TopBeggerRankDto topBeggerRankDto = TopBeggerRankDto.builder()
                        .nickName(user.getNickname())
                        .ratio(beggerRankDto.getRatio()).build();
                topBeggerRankDtos.add(topBeggerRankDto);
            }
            return ResponseEntity.ok(topBeggerRankDtos);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    public ResponseEntity<?> getHallOfBegger(UUID userId) {
        //        String url = "https://j9d208.p.ssafy.io:8282/api/v1/rank/hall-of-begger";
        String url = "http://127.0.0.1:8083/api/v1/rank/hall-of-begger";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        String requestBody = "{\"userId\": \"" + userId + "\", " +
                "\"targetAmount\": \"" + 0 + "\", " +
                "\"currentAmount\": \"" + 0 + "\"}";

        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<List<BeggerNeighborDto>> response = restTemplate.exchange(
                    uri.toString(),
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<BeggerNeighborDto>>() {}
                    );
            List<BeggerNeighborDto> beggerNeighborDtos = response.getBody();
            List<BeggerNeighborResultDto> beggerNeighborResultDtos = new ArrayList<>();
            for (BeggerNeighborDto beggerNeighborDto : beggerNeighborDtos){
                User user = userRepository.findById(beggerNeighborDto.getUserId()).orElseThrow(() ->
                        new NoSuchElementException("존재하지 않는 유저입니다."));
                BeggerNeighborResultDto beggerNeighborResultDto = BeggerNeighborResultDto.builder()
                        .nickName(user.getNickname())
                        .rank(beggerNeighborDto.getRank())
                        .ratio(beggerNeighborDto.getRatio())
                        .build();
                beggerNeighborDtos.add(beggerNeighborDto);
            }
            return ResponseEntity.ok(beggerNeighborDtos);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    public void updateHallOfBegger(User user) {

        UUID userId = user.getId();
        int targetAmount = user.getTargetAmount();
        int currentAmount = user.getCurrentAmount();
        //        String url = "https://j9d208.p.ssafy.io:8282/api/v1/rank/hall-of-begger";
        String url = "http://127.0.0.1:8083/api/v1/rank/hall-of-begger/update";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        String requestBody = "{\"userId\": \"" + userId + "\", " +
                "\"targetAmount\": \"" + targetAmount + "\", " +
                "\"currentAmount\": \"" + currentAmount + "\"}";

        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Void> response =
                    restTemplate.exchange(uri.toString(), HttpMethod.POST,requestEntity, Void.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Failed to update the hall of beggar: HTTP status code "
                        + response.getStatusCode());
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
