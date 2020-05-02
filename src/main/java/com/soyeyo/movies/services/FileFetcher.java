package com.soyeyo.movies.services;

import org.springframework.core.io.Resource;

public interface FileFetcher {

    Resource loadFileAsResource(String fileName);

}
