
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
 *         &lt;element name="SourceControl_Path_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceControl_Provider_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SourceControl_QuietPeriod_Minutes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ReleaseFilter_ReleaseType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Releasefilter_Workflow_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "sourceControlPathName",
    "sourceControlProviderId",
    "sourceControlQuietPeriodMinutes",
    "releaseFilterReleaseTypeCode",
    "releasefilterWorkflowId",
    "activeIndicator"
})
@XmlRootElement(name = "BuildSchedules_CreateOrUpdateSCMTriggered")
public class BuildSchedulesCreateOrUpdateSCMTriggered {

    protected String apiKey;
    @XmlElement(name = "Schedule_Name")
    protected String scheduleName;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "SourceControl_Path_Name")
    protected String sourceControlPathName;
    @XmlElement(name = "SourceControl_Provider_Id", required = true, type = Integer.class, nillable = true)
    protected Integer sourceControlProviderId;
    @XmlElement(name = "SourceControl_QuietPeriod_Minutes", required = true, type = Integer.class, nillable = true)
    protected Integer sourceControlQuietPeriodMinutes;
    @XmlElement(name = "ReleaseFilter_ReleaseType_Code")
    protected String releaseFilterReleaseTypeCode;
    @XmlElement(name = "Releasefilter_Workflow_Id", required = true, type = Integer.class, nillable = true)
    protected Integer releasefilterWorkflowId;
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
     * Gets the value of the sourceControlPathName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceControlPathName() {
        return sourceControlPathName;
    }

    /**
     * Sets the value of the sourceControlPathName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceControlPathName(String value) {
        this.sourceControlPathName = value;
    }

    /**
     * Gets the value of the sourceControlProviderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSourceControlProviderId() {
        return sourceControlProviderId;
    }

    /**
     * Sets the value of the sourceControlProviderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSourceControlProviderId(Integer value) {
        this.sourceControlProviderId = value;
    }

    /**
     * Gets the value of the sourceControlQuietPeriodMinutes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSourceControlQuietPeriodMinutes() {
        return sourceControlQuietPeriodMinutes;
    }

    /**
     * Sets the value of the sourceControlQuietPeriodMinutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSourceControlQuietPeriodMinutes(Integer value) {
        this.sourceControlQuietPeriodMinutes = value;
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
