package com.ceng557.assignment.config;

import com.ceng557.assignment.rest.util.DaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Component
@Lazy(false)
public class DaoUtilConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DaoUtilConfiguration.class);

    @PostConstruct
    public void init(){
        logger.info("initializing DaoUtil via loading sql_queries.xml");
        try {
            DaoUtil.initializeAllQueries();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Could not read sql_queries.xml file...", e);
        }
    }

}
