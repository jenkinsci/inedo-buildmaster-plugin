
package com.inedo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apiKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Application_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReleaseNumber_Scheme_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IssueTracking_Provider_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BuildNumber_Scheme_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllowMultipleActiveReleases_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllowMultipleActiveBuilds_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApplicationGroup_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "apiKey",
    "applicationName",
    "releaseNumberSchemeName",
    "issueTrackingProviderId",
    "buildNumberSchemeName",
    "allowMultipleActiveReleasesIndicator",
    "allowMultipleActiveBuildsIndicator",
    "applicationGroupId"
})
@XmlRootElement(name = "Applications_CreateApplication")
public class ApplicationsCreateApplication {

    protected String apiKey;
    @XmlElement(name = "Application_Name")
    protected String applicationName;
    @XmlElement(name = "ReleaseNumber_Scheme_Name")
    protected String releaseNumberSchemeName;
    @XmlElement(name = "IssueTracking_Provider_Id", required = true, type = Integer.class, nillable = true)
    protected Integer issueTrackingProviderId;
    @XmlElement(name = "BuildNumber_Scheme_Name")
    protected String buildNumberSchemeName;
    @XmlElement(name = "AllowMultipleActiveReleases_Indicator")
    protected String allowMultipleActiveReleasesIndicator;
    @XmlElement(name = "AllowMultipleActiveBuilds_Indicator")
    protected String allowMultipleActiveBuildsIndicator;
    @XmlElement(name = "ApplicationGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationGroupId;

    /**
     * Gets the value of the apiKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the value of the apiKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiKey(String value) {
        this.apiKey = value;
    }

    /**
     * Gets the value of the applicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the value of the applicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationName(String value) {
        this.applicationName = value;
    }

    /**
     * Gets the value of the releaseNumberSchemeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseNumberSchemeName() {
        return releaseNumberSchemeName;
    }

    /**
     * Sets the value of the releaseNumberSchemeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseNumberSchemeName(String value) {
        this.releaseNumberSchemeName = value;
    }

    /**
     * Gets the value of the issueTrackingProviderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIssueTrackingProviderId() {
        return issueTrackingProviderId;
    }

    /**
     * Sets the value of the issueTrackingProviderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIssueTrackingProviderId(Integer value) {
        this.issueTrackingProviderId = value;
    }

    /**
     * Gets the value of the buildNumberSchemeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildNumberSchemeName() {
        return buildNumberSchemeName;
    }

    /**
     * Sets the value of the buildNumberSchemeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildNumberSchemeName(String value) {
        this.buildNumberSchemeName = value;
    }

    /**
     * Gets the value of the allowMultipleActiveReleasesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowMultipleActiveReleasesIndicator() {
        return allowMultipleActiveReleasesIndicator;
    }

    /**
     * Sets the value of the allowMultipleActiveReleasesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowMultipleActiveReleasesIndicator(String value) {
        this.allowMultipleActiveReleasesIndicator = value;
    }

    /**
     * Gets the value of the allowMultipleActiveBuildsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowMultipleActiveBuildsIndicator() {
        return allowMultipleActiveBuildsIndicator;
    }

    /**
     * Sets the value of the allowMultipleActiveBuildsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowMultipleActiveBuildsIndicator(String value) {
        this.allowMultipleActiveBuildsIndicator = value;
    }

    /**
     * Gets the value of the applicationGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getApplicationGroupId() {
        return applicationGroupId;
    }

    /**
     * Sets the value of the applicationGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setApplicationGroupId(Integer value) {
        this.applicationGroupId = value;
    }

}
