package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.begger.BeggerRankDto;
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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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
            return ResponseEntity.ok(beggerRankDtos);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    public ResponseEntity<?> getHallOfBegger(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            String userName = user.getNickname();
            //        String url = "https://j9d208.p.ssafy.io:8282/api/v1/rank/hall-of-begger";
            String url = "http://127.0.0.1:8083/api/v1/rank/hall-of-begger";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            String requestBody = "{\"userName\": \"" + userName + "\", " +
                    "\"targetAmount\": \"" + 0 + "\", " +
                    "\"currentAmount\": \"" + 0 + "\"}";

            HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);
            try {
                ResponseEntity<Integer> response = restTemplate.exchange(uri.toString(), HttpMethod.POST,requestEntity, Integer.class);
                Integer rank = response.getBody();
                return ResponseEntity.ok(rank);
            }catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.ok(false);
            }
        }else{
            throw new NoSuchElementException("사용자를 찾을 수 없습니다.");
        }
    }

    public void updateHallOfBegger(User user) {

        String userName = user.getNickname();
        int targetAmount = user.getTargetAmount();
        int currentAmount = user.getCurrentAmount();
        //        String url = "https://j9d208.p.ssafy.io:8282/api/v1/rank/hall-of-begger";
        String url = "http://127.0.0.1:8083/api/v1/rank/hall-of-begger/update";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        String requestBody = "{\"userName\": \"" + userName + "\", " +
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
