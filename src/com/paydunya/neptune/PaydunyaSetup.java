package com.paydunya.neptune;

public class PaydunyaSetup {

    private String privateKey;
    private String publicKey;
    private String masterKey;
    private String token;
    private String mode;
    final String ROOT_URL_BASE = "https://app.paydunya.com";
    final String LIVE_CHECKOUT_INVOICE_BASE_URL = "/api/v1/checkout-invoice/create";
    final String TEST_CHECKOUT_INVOICE_BASE_URL = "/sandbox-api/v1/checkout-invoice/create";
    final String LIVE_CHECKOUT_CONFIRM_BASE_URL = "/api/v1/checkout-invoice/confirm/";
    final String TEST_CHECKOUT_CONFIRM_BASE_URL = "/sandbox-api/v1/checkout-invoice/confirm/";
    final String LIVE_OPR_BASE_URL = "/api/v1/opr/create";
    final String TEST_OPR_BASE_URL = "/sandbox-api/v1/opr/create";
    final String LIVE_OPR_CHARGE_BASE_URL = "/api/v1/opr/charge";
    final String TEST_OPR_CHARGE_BASE_URL = "/sandbox-api/v1/opr/charge";
    final String LIVE_DIRECT_PAY_CREDIT_URL = "/api/v1/direct-pay/credit-account";
    final String TEST_DIRECT_PAY_CREDIT_URL = "/sandbox-api/v1/direct-pay/credit-account";
    
    
    public PaydunyaSetup(String paramString1, String paramString2,
            String paramString3, String paramString4, String paramString5) {
        this.masterKey = paramString1;
        this.privateKey = paramString2;
        this.publicKey = paramString3;
        this.token = paramString4;
        this.mode = paramString5;
    }

    public PaydunyaSetup() {
    }

    public void setMasterKey(String paramString) {
        this.masterKey = paramString;
    }

    public void setPrivateKey(String paramString) {
        this.privateKey = paramString;
    }

    public void setPublicKey(String paramString) {
        this.publicKey = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }

    public void setMode(String paramString) {
        this.mode = paramString;
    }

    public String getMasterKey() {
        return this.masterKey;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public String getToken() {
        return this.token;
    }

    public String getMode() {
        return this.mode;
    }

    public String getCheckoutInvoiceUrl() {
        if (this.mode == "live") {
            return  ROOT_URL_BASE + LIVE_CHECKOUT_INVOICE_BASE_URL;
        }
        return  ROOT_URL_BASE + TEST_CHECKOUT_INVOICE_BASE_URL;
    }

    public String getCheckoutConfirmUrl() {
        if (this.mode == "live") {
            return  ROOT_URL_BASE + LIVE_CHECKOUT_CONFIRM_BASE_URL;
        }
        return  ROOT_URL_BASE + TEST_CHECKOUT_CONFIRM_BASE_URL;
    }

    public String getOPRInvoiceUrl() {
        if (this.mode == "live") {
            return  ROOT_URL_BASE + LIVE_OPR_BASE_URL;
        }
        return  ROOT_URL_BASE + TEST_OPR_BASE_URL;
    }

    public String getOPRChargeUrl() {
        if (this.mode == "live") {
            return  ROOT_URL_BASE + LIVE_OPR_CHARGE_BASE_URL;
        }
        return  ROOT_URL_BASE + TEST_OPR_CHARGE_BASE_URL;
    }

    public String getDirectPayCreditUrl() {
        if (this.mode == "live") {
            return  ROOT_URL_BASE + LIVE_DIRECT_PAY_CREDIT_URL;
        }
        return  ROOT_URL_BASE + TEST_DIRECT_PAY_CREDIT_URL;
    }
}
