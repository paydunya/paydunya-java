package com.paydunya.neptune;

import tk.json.simple.JSONObject;

public class PaydunyaCheckoutStore {
	private String name = "Untitled Store";
	  private String tagline;
	  private String postalAddress;
	  private String phoneNumber;
	  private String logoUrl;
	  private String websiteUrl;
	  private String returnUrl;
	  private String cancelUrl;
	  private String callbackUrl;

	  public PaydunyaCheckoutStore(String paramString1, String paramString2)
	  {
	    setName(paramString1);
	    setTagline(paramString2);
	  }

	  public PaydunyaCheckoutStore(String paramString)
	  {
	    setName(paramString);
	  }

	  public PaydunyaCheckoutStore() {}

	  public void setName(String paramString)
	  {
	    this.name = paramString;
	  }

	  public void setTagline(String paramString)
	  {
	    this.tagline = paramString;
	  }

	  public void setPostalAddress(String paramString)
	  {
	    this.postalAddress = paramString;
	  }

	  public void setPhoneNumber(String paramString)
	  {
	    this.phoneNumber = paramString;
	  }

	  public void setWebsiteUrl(String paramString)
	  {
	    this.websiteUrl = paramString;
	  }

	  public void setLogoUrl(String paramString)
	  {
	    this.logoUrl = paramString;
	  }

	  public void setCancelUrl(String paramString)
	  {
	    this.cancelUrl = paramString;
	  }

	  public void setReturnUrl(String paramString)
	  {
	    this.returnUrl = paramString;
	  }

	  public void setCallbackUrl(String paramString)
	  {
	    this.callbackUrl = paramString;
	  }

	  public String getName()
	  {
	    return this.name;
	  }

	  public String getTagline()
	  {
	    return this.tagline;
	  }

	  public String getPostalAddress()
	  {
	    return this.postalAddress;
	  }

	  public String getPhoneNumber()
	  {
	    return this.phoneNumber;
	  }

	  public String getWebsiteUrl()
	  {
	    return this.websiteUrl;
	  }

	  public String getLogoUrl()
	  {
	    return this.logoUrl;
	  }

	  public String getCancelUrl()
	  {
	    return this.cancelUrl;
	  }

	  public String getReturnUrl()
	  {
	    return this.returnUrl;
	  }

	  public String getCallbackUrl()
	  {
	    return this.callbackUrl;
	  }

	  public JSONObject getSettings()
	  {
	    JSONObject localJSONObject = new JSONObject();
	    localJSONObject.put("name", getName());
	    localJSONObject.put("tagline", getTagline());
	    localJSONObject.put("postal_address", getPostalAddress());
	    localJSONObject.put("phone", getPhoneNumber());
	    localJSONObject.put("logo_url", getLogoUrl());
	    localJSONObject.put("website_url", getWebsiteUrl());
	    return localJSONObject;
	  }
}
