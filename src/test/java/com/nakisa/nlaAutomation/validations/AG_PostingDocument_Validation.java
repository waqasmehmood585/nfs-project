package com.nakisa.nlaAutomation.validations;

public class AG_PostingDocument_Validation {

    private String documentType;
    private String documentDate;
    private String postingDate;
    private String fiscalYear;
    private String fiscalPeriod;
    private String internalStatus;
    private String externalStatus;
    private String journalDate;
    private String companyCode;
    private String standard;
    private String accountNumber;
    private String payment;
    private String amountContract;
    private String currencyContract;
    private String amountCompany;
    private String currencyCompany;
    private String amountGroup;
    private String currencyGroup;
    private String journalLevel;

    public AG_PostingDocument_Validation() {
    }

    public AG_PostingDocument_Validation(String documentType, String documentDate, String postingDate, String fiscalYear, String fiscalPeriod, String internalStatus,
                                         String externalStatus, String journalDate, String companyCode, String standard, String accountNumber, String payment,
                                         String amountContract, String currencyContract, String amountCompany, String currencyCompany, String amountGroup, String currencyGroup) {
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.postingDate = postingDate;
        this.fiscalYear = fiscalYear;
        this.fiscalPeriod = fiscalPeriod;
        this.internalStatus = internalStatus;
        this.externalStatus = externalStatus;
        this.journalDate = journalDate;
        this.companyCode = companyCode;
        this.standard = standard;
        this.accountNumber = accountNumber;
        this.payment = payment;
        this.amountContract = amountContract;
        this.currencyContract = currencyContract;
        this.amountCompany = amountCompany;
        this.currencyCompany = currencyCompany;
        this.amountGroup = amountGroup;
        this.currencyGroup = currencyGroup;
    }

    public AG_PostingDocument_Validation(String documentType, String documentDate, String postingDate, String fiscalYear, String fiscalPeriod, String internalStatus, String externalStatus, String journalDate, String companyCode, String standard, String accountNumber, String payment, String amountContract, String currencyContract, String amountCompany, String currencyCompany, String amountGroup, String currencyGroup, String journalLevel) {
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.postingDate = postingDate;
        this.fiscalYear = fiscalYear;
        this.fiscalPeriod = fiscalPeriod;
        this.internalStatus = internalStatus;
        this.externalStatus = externalStatus;
        this.journalDate = journalDate;
        this.companyCode = companyCode;
        this.standard = standard;
        this.accountNumber = accountNumber;
        this.payment = payment;
        this.amountContract = amountContract;
        this.currencyContract = currencyContract;
        this.amountCompany = amountCompany;
        this.currencyCompany = currencyCompany;
        this.amountGroup = amountGroup;
        this.currencyGroup = currencyGroup;
        this.journalLevel = journalLevel;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getFiscalPeriod() {
        return fiscalPeriod;
    }

    public void setFiscalPeriod(String fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }

    public String getInternalStatus() {
        return internalStatus;
    }

    public void setInternalStatus(String internalStatus) {
        this.internalStatus = internalStatus;
    }

    public String getExternalStatus() {
        return externalStatus;
    }

    public void setExternalStatus(String externalStatus) {
        this.externalStatus = externalStatus;
    }

    public String getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(String journalDate) {
        this.journalDate = journalDate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAmountContract() {
        return amountContract;
    }

    public void setAmountContract(String amountContract) {
        this.amountContract = amountContract;
    }

    public String getCurrencyContract() {
        return currencyContract;
    }

    public void setCurrencyContract(String currencyContract) {
        this.currencyContract = currencyContract;
    }

    public String getAmountCompany() {
        return amountCompany;
    }

    public void setAmountCompany(String amountCompany) {
        this.amountCompany = amountCompany;
    }

    public String getCurrencyCompany() {
        return currencyCompany;
    }

    public void setCurrencyCompany(String currencyCompany) {
        this.currencyCompany = currencyCompany;
    }

    public String getAmountGroup() {
        return amountGroup;
    }

    public void setAmountGroup(String amountGroup) {
        this.amountGroup = amountGroup;
    }

    public String getCurrencyGroup() {
        return currencyGroup;
    }

    public void setCurrencyGroup(String currencyGroup) {
        this.currencyGroup = currencyGroup;
    }

    public String getJournalLevel() {return journalLevel;}
    public void setJournalLevel(String journalLevel) {this.journalLevel = journalLevel;}
}
