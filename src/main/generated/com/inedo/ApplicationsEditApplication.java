
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
 *         &lt;element name="Application_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Application_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Application_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApplicationGroup_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AllowMultipleActiveReleases_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllowMultipleActiveBuilds_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VariableSupport_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReleaseNumber_Scheme_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BuildNumber_Scheme_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "applicationId",
    "applicationName",
    "applicationDescription",
    "applicationGroupId",
    "allowMultipleActiveReleasesIndicator",
    "allowMultipleActiveBuildsIndicator",
    "variableSupportCode",
    "releaseNumberSchemeName",
    "buildNumberSchemeName"
})
@XmlRootElement(name = "Applications_EditApplication")
public class ApplicationsEditApplication {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Application_Name")
    protected String applicationName;
    @XmlElement(name = "Application_Description")
    protected String applicationDescription;
    @XmlElement(name = "ApplicationGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationGroupId;
    @XmlElement(name = "AllowMultipleActiveReleases_Indicator")
    protected String allowMultipleActiveReleasesIndicator;
    @XmlElement(name = "AllowMultipleActiveBuilds_Indicator")
    protected String allowMultipleActiveBuildsIndicator;
    @XmlElement(name = "VariableSupport_Code")
    protected String variableSupportCode;
    @XmlElement(name = "ReleaseNumber_Scheme_Name")
    protected String releaseNumberSchemeName;
    @XmlElement(name = "BuildNumber_Scheme_Name")
    protected String buildNumberSchemeName;

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
     * Gets the value of the applicationId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the value of the applicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setApplicationId(Integer value) {
        this.applicationId = value;
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
     * Gets the value of the applicationDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationDescription() {
        return applicationDescription;
    }

    /**
     * Sets the value of the applicationDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationDescription(String value) {
        this.applicationDescription = value;
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
     * Gets the value of the variableSupportCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariableSupportCode() {
        return variableSupportCode;
    }

    /**
     * Sets the value of the variableSupportCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariableSupportCode(String value) {
        this.variableSupportCode = value;
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

}
