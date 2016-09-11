package com.paydunya.neptune;

import tk.json.simple.JSONObject;

public class PaydunyaOnsiteInvoice extends PaydunyaCheckoutInvoice {
    public String invoiceToken;

    public PaydunyaOnsiteInvoice(PaydunyaSetup paramPaydunyaSetup,
            PaydunyaCheckoutStore paramPaydunyaCheckoutStore) {
        super(paramPaydunyaSetup, paramPaydunyaCheckoutStore);
    }

    public boolean create(String paramString) {
        JSONObject localJSONObject1 = new JSONObject();
        JSONObject localJSONObject2 = new JSONObject();
        JSONObject localJSONObject3 = new JSONObject();
        JSONObject localJSONObject4 = new JSONObject();
        JSONObject localJSONObject5 = new JSONObject();

        localJSONObject4.put("items", this.items);
        localJSONObject4.put("taxes", this.taxes);
        localJSONObject4.put("total_amount", Double.valueOf(getTotalAmount()));
        localJSONObject4.put("description", getDescription());
        localJSONObject1.put("invoice", localJSONObject4);
        localJSONObject1.put("store", this.store.getSettings());
        localJSONObject5.put("cancel_url", getCancelUrl());
        localJSONObject5.put("return_url", getReturnUrl());
        localJSONObject5.put("callback_url", getCallbackUrl());
        localJSONObject1.put("actions", localJSONObject5);

        localJSONObject2.put("account_alias", paramString);

        localJSONObject3.put("invoice_data", localJSONObject1);
        localJSONObject3.put("opr_data", localJSONObject2);
        localJSONObject3.put("custom_data", this.customData);

        JSONObject localJSONObject6 = this.utility.jsonRequest(
                this.setup.getOPRInvoiceUrl(), localJSONObject3.toString());

        this.responseText = localJSONObject6.get("response_text").toString();
        this.responseCode = localJSONObject6.get("response_code").toString();
        if (this.responseCode.equals("00")) {
            this.token = localJSONObject6.get("token").toString();
            this.invoiceToken = localJSONObject6.get("invoice_token")
                    .toString();
            this.responseText = localJSONObject6.get("description").toString();
            this.status = SUCCESS;
            return true;
        }
        this.status = FAIL;
        return false;
    }

    public Boolean charge(String paramString1, String paramString2) {
        JSONObject localJSONObject1 = new JSONObject();
        localJSONObject1.put("token", paramString1);
        localJSONObject1.put("confirm_token", paramString2);

        JSONObject localJSONObject2 = this.utility.jsonRequest(
                this.setup.getOPRChargeUrl(), localJSONObject1.toString());
        JSONObject localJSONObject3 = new JSONObject();
        Boolean localBoolean = Boolean.valueOf(false);
        if (localJSONObject2.size() > 0) {
            if (localJSONObject2.get("response_code").equals("00")) {
                localJSONObject3 = (JSONObject) localJSONObject2
                        .get("invoice_data");
                this.invoice = ((JSONObject) localJSONObject3.get("invoice"));
                this.status = localJSONObject3.get("status").toString();
                setReceiptUrl(localJSONObject3.get("receipt_url").toString());
                this.customData = this.utility.pushJSON(localJSONObject3
                        .get("custom_data"));
                this.customer = this.utility.pushJSON(localJSONObject3
                        .get("customer"));
                this.taxes = this.utility.pushJSON(this.invoice.get("taxes"));
                this.items = this.utility.pushJSON(this.invoice.get("items"));
                setTotalAmount(Double.parseDouble(this.invoice.get("total_amount").toString()));

                this.responseText = localJSONObject2.get("response_text")
                        .toString();
                this.responseCode = "00";
                localBoolean = Boolean.valueOf(true);
            } else {
                this.status = FAIL;
                this.responseText = localJSONObject2.get("response_text")
                        .toString();
                this.responseCode = localJSONObject2.get("response_code")
                        .toString();
            }
        } else {
            this.responseText = "An Unknown PAYDUNYA Server Error Occured.";
            this.responseCode = "4002";
            this.status = FAIL;
        }
        return localBoolean;
    }
}
