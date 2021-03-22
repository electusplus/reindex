package com.electusplus.reindex;

import com.electusplus.reindex.app_settings.AppPOJO;
import com.electusplus.reindex.app_settings.AppSettingsPOJO;
import com.electusplus.reindex.constants.EAppSettings;
import com.electusplus.reindex.data_warehouse.DataWarehouse;
import com.electusplus.reindex.rest.MainRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Reindex {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        logger.info("Welcome to Reindex application");
        final String homeFolder = args[0];
        if (homeFolder == null) {
            logger.info("The home folder doesn't specified ");
        }
        logger.info("Application home folder: " + homeFolder);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        AppSettingsPOJO appSettings;
        try {
            AppPOJO appConfiguration = mapper.readValue(new File(homeFolder +
                    EAppSettings.CONFIG_FOLDER.getStringValueOfSetting() +
                    EAppSettings.CONFIGURATION_FILE.getStringValueOfSetting()), AppPOJO.class);
            appSettings = new AppSettingsPOJO(appConfiguration, homeFolder);
        } catch (IOException e) {
            throw new Exception("Configuration folder not present or Incorrect configuration file" + e);
        }

        DataWarehouse dataWarehouse = DataWarehouse.getInstance();
        dataWarehouse.init(appSettings);
//        Transaction transaction = ElasticApm.startTransaction();
//        try {
//            transaction.setName("Run server");
//            transaction.setType(Transaction.TYPE_REQUEST);
//        } catch (Exception e) {
//            transaction.captureException(e);
//            throw e;
//        } finally {
//            transaction.end();
//        }
        MainRest restApi = new MainRest(appSettings);
        restApi.runServer(appSettings.getApp().getHost(), appSettings.getApp().getPort());
    }

    public static void stop() {
        System.exit(0);
    }
}
