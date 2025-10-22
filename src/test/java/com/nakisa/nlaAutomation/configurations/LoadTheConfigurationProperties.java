package com.nakisa.nlaAutomation.configurations;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@Validated
@PropertySource("classpath:config.properties")
@PropertySource("classpath:secrets.properties")
@ConfigurationProperties
public class LoadTheConfigurationProperties {

	private String email;
	private String browser;
	private Boolean runAutomationOnNCPBuild;
	private String whichBaselineToUseForValidation;
	private String wantToGenerateBaseline;
	private Double allowedDifferenceToSkip;
	private String pfizerCompanyCode;
	private String pfizerCostCenter;
	private Boolean parallelCurrency;
	private String erpSystem;
	private int postingsToCheck;
	private String buildURL;
	private int reTryCount;
	private String runEnvironment;
	private String cockpitLink;
	private String password;
	private String envName;
	private String tokens;
	private String schedulerEmail;
	private String essToken1;
	private String schedulerToken;

}
