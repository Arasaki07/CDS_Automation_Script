package com.moodys.ma.ds;

import com.moodys.ma.ds.entities.LegalEntitiesRecord;
import com.moodys.ma.ds.repositories.LegalEntitiesRepository;
import com.moodys.ma.ds.rest.RestExecutorService;
import com.moodys.ma.ds.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class LegalService {

    @Autowired
    private final RestExecutorService restExecutorService;
    private final LegalEntitiesRepository legalEntitiesRepository;

    private static final String LEGAL_URL = "/v0/legalEntities/firmographics/legal";

    @Value("${legal.api.url}")
    private String legalApiUrl;

    @Value("${api.auth.header-name}")
    private String headerName;

    @Value("${api.auth.token}")
    private String authToken;


    public RestResponse getLegalService(String idType, String id) {
        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put(headerName, authToken);
        return restExecutorService.execute(buildLegalUrl(idType, id),"", customHeaders, HttpMethod.GET);
    }

    public LegalEntitiesRecord getLegalEntitiesRecord(String mongo_query) {

        LegalEntitiesRecord legalEntitiesRecord = legalEntitiesRepository.findByEntityId(mongo_query);
        return legalEntitiesRecord;
    }


    private String buildLegalUrl(String idType, String id) {
        StringBuilder url = new StringBuilder(legalApiUrl + LEGAL_URL);
        getLegalUrl(idType, id, url);
        return url.toString();
    }

    private static void getLegalUrl(String idType, String id, StringBuilder url) {
        if("bvdid".equals(idType))
            url.append("?bvdid=").append(id).append("&");

        if ("orbisid".equals(idType))
            url.append("?orbisid=").append(id).append("&");

        if ("identifier".equals(idType))
            url.append("?identifier=").append(id).append("&");

        if ("lei".equals(idType))
            url.append("?lei=").append(id).append("&");

        if ("country".equals(idType))
            url.append("?country=").append(id).append("&");

        if ("status".equals(idType))
            url.append("?status=").append(id).append("&");


    }
}
