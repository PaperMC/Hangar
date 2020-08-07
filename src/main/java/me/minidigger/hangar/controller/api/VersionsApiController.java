package me.minidigger.hangar.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.minidigger.hangar.config.hangar.HangarConfig;
import me.minidigger.hangar.model.generated.DeployVersionInfo;
import me.minidigger.hangar.model.generated.PaginatedVersionResult;
import me.minidigger.hangar.model.generated.Pagination;
import me.minidigger.hangar.model.generated.Version;
import me.minidigger.hangar.model.generated.VersionStatsDay;
import me.minidigger.hangar.service.ApiService;
import me.minidigger.hangar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static me.minidigger.hangar.util.ApiUtil.limitOrDefault;
import static me.minidigger.hangar.util.ApiUtil.offsetOrZero;

@Controller
public class VersionsApiController implements VersionsApi {


    private static final Logger log = LoggerFactory.getLogger(VersionsApiController.class);

    private final HangarConfig hangarConfig;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final ApiService apiService;



    @Autowired
    public VersionsApiController(HangarConfig hangarConfig, ObjectMapper objectMapper, UserService userService, ApiService apiService) {
        this.hangarConfig = hangarConfig;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.apiService = apiService;
    }

    @Override
    public ResponseEntity<Version> deployVersion(DeployVersionInfo pluginInfo, MultipartFile pluginFile, String pluginId) {
        try {
            return new ResponseEntity<>(objectMapper.readValue("{\n  \"visibility\" : \"public\",\n  \"stats\" : {\n    \"downloads\" : 0\n  },\n  \"author\" : \"author\",\n  \"file_info\" : {\n    \"size_bytes\" : 6,\n    \"md_5_hash\" : \"md_5_hash\",\n    \"name\" : \"name\"\n  },\n  \"name\" : \"name\",\n  \"created_at\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"description\" : \"description\",\n  \"dependencies\" : [ {\n    \"plugin_id\" : \"plugin_id\",\n    \"version\" : \"version\"\n  }, {\n    \"plugin_id\" : \"plugin_id\",\n    \"version\" : \"version\"\n  } ],\n  \"review_state\" : \"unreviewed\",\n  \"tags\" : [ {\n    \"data\" : \"data\",\n    \"color\" : {\n      \"background\" : \"background\",\n      \"foreground\" : \"foreground\"\n    },\n    \"name\" : \"name\"\n  }, {\n    \"data\" : \"data\",\n    \"color\" : {\n      \"background\" : \"background\",\n      \"foreground\" : \"foreground\"\n    },\n    \"name\" : \"name\"\n  } ]\n}", Version.class), HttpStatus.OK); // TODO Implement me
        } catch (IOException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<PaginatedVersionResult> listVersions(String pluginId, List<String> tags, Long limit, Long offset) {
        // TODO users
        List<Version> versions = apiService.getVersionList(pluginId, tags, false, limitOrDefault(limit, hangarConfig.projects.getInitVersionLoad()), offsetOrZero(offset), null);
        long versionCount = apiService.getVersionCount(pluginId, tags, false, null);
        return new ResponseEntity<>(new PaginatedVersionResult().result(versions).pagination(new Pagination().limit(limit).offset(offset).count(versionCount)), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Version> showVersion(String pluginId, String name) {
        // TODO handling users
        Version version = apiService.getVersion(pluginId, name, false, null);
        if (version == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(version, HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<Map<String, VersionStatsDay>> showVersionStats(String pluginId, String version, LocalDate fromDate, LocalDate toDate) {
        try {
            return new ResponseEntity<Map<String, VersionStatsDay>>(objectMapper.readValue("{\n  \"key\" : {\n    \"downloads\" : 0\n  }\n}", Map.class), HttpStatus.OK); // TODO Implement me
        } catch (IOException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
