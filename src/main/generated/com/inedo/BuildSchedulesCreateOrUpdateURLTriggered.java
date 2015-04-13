
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
 *         &lt;element name="Schedule_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Application_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Url_AllowedIP_CSV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Url_Username_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Url_Password_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReleaseFilter_ReleaseType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Releasefilter_Workflow_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BuildImporter_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "scheduleName",
    "applicationId",
    "urlAllowedIPCSV",
    "urlUsernameText",
    "urlPasswordText",
    "releaseFilterReleaseTypeCode",
    "releasefilterWorkflowId",
    "buildImporterConfiguration",
    "activeIndicator"
})
@XmlRootElement(name = "BuildSchedules_CreateOrUpdateURLTriggered")
public class BuildSchedulesCreateOrUpdateURLTriggered {

    protected String apiKey;
    @XmlElement(name = "Schedule_Name")
    protected String scheduleName;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Url_AllowedIP_CSV")
    protected String urlAllowedIPCSV;
    @XmlElement(name = "Url_Username_Text")
    protected String urlUsernameText;
    @XmlElement(name = "Url_Password_Text")
    protected String urlPasswordText;
    @XmlElement(name = "ReleaseFilter_ReleaseType_Code")
    protected String releaseFilterReleaseTypeCode;
    @XmlElement(name = "Releasefilter_Workflow_Id", required = true, type = Integer.class, nillable = true)
    protected Integer releasefilterWorkflowId;
    @XmlElement(name = "BuildImporter_Configuration")
    protected String buildImporterConfiguration;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;

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
     * Gets the value of the scheduleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleName() {
        return scheduleName;
    }

    /**
     * Sets the value of the scheduleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleName(String value) {
        this.scheduleName = value;
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
     * Gets the value of the urlAllowedIPCSV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlAllowedIPCSV() {
        return urlAllowedIPCSV;
    }

    /**
     * Sets the value of the urlAllowedIPCSV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlAllowedIPCSV(String value) {
        this.urlAllowedIPCSV = value;
    }

    /**
     * Gets the value of the urlUsernameText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlUsernameText() {
        return urlUsernameText;
    }

    /**
     * Sets the value of the urlUsernameText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlUsernameText(String value) {
        this.urlUsernameText = value;
    }

    /**
     * Gets the value of the urlPasswordText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlPasswordText() {
        return urlPasswordText;
    }

    /**
     * Sets the value of the urlPasswordText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlPasswordText(String value) {
        this.urlPasswordText = value;
    }

    /**
     * Gets the value of the releaseFilterReleaseTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseFilterReleaseTypeCode() {
        return releaseFilterReleaseTypeCode;
    }

    /**
     * Sets the value of the releaseFilterReleaseTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseFilterReleaseTypeCode(String value) {
        this.releaseFilterReleaseTypeCode = value;
    }

    /**
     * Gets the value of the releasefilterWorkflowId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReleasefilterWorkflowId() {
        return releasefilterWorkflowId;
    }

    /**
     * Sets the value of the releasefilterWorkflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReleasefilterWorkflowId(Integer value) {
        this.releasefilterWorkflowId = value;
    }

    /**
     * Gets the value of the buildImporterConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildImporterConfiguration() {
        return buildImporterConfiguration;
    }

    /**
     * Sets the value of the buildImporterConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildImporterConfiguration(String value) {
        this.buildImporterConfiguration = value;
    }

    /**
     * Gets the value of the activeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveIndicator() {
        return activeIndicator;
    }

    /**
     * Sets the value of the activeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveIndicator(String value) {
        this.activeIndicator = value;
    }

}
