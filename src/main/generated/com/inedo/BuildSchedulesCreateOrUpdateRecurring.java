
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
 *         &lt;element name="StartTime_Minutes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EndTime_Minutes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DailyInterval_Minutes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Recurrence_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecurrenceFrequency_Value" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RecurrenceWeeklyDay_Mask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecurrenceMonthlyDay_Value" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RecurrenceMonthlyRelativity_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecurrenceMonthlyRelativityDay_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecurrenceMonthlyRelativityWeek_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecurrenceMonthlyRelativityWeekDay_Value" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "startTimeMinutes",
    "endTimeMinutes",
    "dailyIntervalMinutes",
    "recurrenceType",
    "recurrenceFrequencyValue",
    "recurrenceWeeklyDayMask",
    "recurrenceMonthlyDayValue",
    "recurrenceMonthlyRelativityType",
    "recurrenceMonthlyRelativityDayType",
    "recurrenceMonthlyRelativityWeekType",
    "recurrenceMonthlyRelativityWeekDayValue",
    "releaseFilterReleaseTypeCode",
    "releasefilterWorkflowId",
    "buildImporterConfiguration",
    "activeIndicator"
})
@XmlRootElement(name = "BuildSchedules_CreateOrUpdateRecurring")
public class BuildSchedulesCreateOrUpdateRecurring {

    protected String apiKey;
    @XmlElement(name = "Schedule_Name")
    protected String scheduleName;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "StartTime_Minutes", required = true, type = Integer.class, nillable = true)
    protected Integer startTimeMinutes;
    @XmlElement(name = "EndTime_Minutes", required = true, type = Integer.class, nillable = true)
    protected Integer endTimeMinutes;
    @XmlElement(name = "DailyInterval_Minutes", required = true, type = Integer.class, nillable = true)
    protected Integer dailyIntervalMinutes;
    @XmlElement(name = "Recurrence_Type")
    protected String recurrenceType;
    @XmlElement(name = "RecurrenceFrequency_Value", required = true, type = Integer.class, nillable = true)
    protected Integer recurrenceFrequencyValue;
    @XmlElement(name = "RecurrenceWeeklyDay_Mask")
    protected String recurrenceWeeklyDayMask;
    @XmlElement(name = "RecurrenceMonthlyDay_Value", required = true, type = Integer.class, nillable = true)
    protected Integer recurrenceMonthlyDayValue;
    @XmlElement(name = "RecurrenceMonthlyRelativity_Type")
    protected String recurrenceMonthlyRelativityType;
    @XmlElement(name = "RecurrenceMonthlyRelativityDay_Type")
    protected String recurrenceMonthlyRelativityDayType;
    @XmlElement(name = "RecurrenceMonthlyRelativityWeek_Type")
    protected String recurrenceMonthlyRelativityWeekType;
    @XmlElement(name = "RecurrenceMonthlyRelativityWeekDay_Value", required = true, type = Integer.class, nillable = true)
    protected Integer recurrenceMonthlyRelativityWeekDayValue;
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
     * Gets the value of the startTimeMinutes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartTimeMinutes() {
        return startTimeMinutes;
    }

    /**
     * Sets the value of the startTimeMinutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartTimeMinutes(Integer value) {
        this.startTimeMinutes = value;
    }

    /**
     * Gets the value of the endTimeMinutes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEndTimeMinutes() {
        return endTimeMinutes;
    }

    /**
     * Sets the value of the endTimeMinutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEndTimeMinutes(Integer value) {
        this.endTimeMinutes = value;
    }

    /**
     * Gets the value of the dailyIntervalMinutes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDailyIntervalMinutes() {
        return dailyIntervalMinutes;
    }

    /**
     * Sets the value of the dailyIntervalMinutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDailyIntervalMinutes(Integer value) {
        this.dailyIntervalMinutes = value;
    }

    /**
     * Gets the value of the recurrenceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurrenceType() {
        return recurrenceType;
    }

    /**
     * Sets the value of the recurrenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurrenceType(String value) {
        this.recurrenceType = value;
    }

    /**
     * Gets the value of the recurrenceFrequencyValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecurrenceFrequencyValue() {
        return recurrenceFrequencyValue;
    }

    /**
     * Sets the value of the recurrenceFrequencyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecurrenceFrequencyValue(Integer value) {
        this.recurrenceFrequencyValue = value;
    }

    /**
     * Gets the value of the recurrenceWeeklyDayMask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurrenceWeeklyDayMask() {
        return recurrenceWeeklyDayMask;
    }

    /**
     * Sets the value of the recurrenceWeeklyDayMask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurrenceWeeklyDayMask(String value) {
        this.recurrenceWeeklyDayMask = value;
    }

    /**
     * Gets the value of the recurrenceMonthlyDayValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecurrenceMonthlyDayValue() {
        return recurrenceMonthlyDayValue;
    }

    /**
     * Sets the value of the recurrenceMonthlyDayValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecurrenceMonthlyDayValue(Integer value) {
        this.recurrenceMonthlyDayValue = value;
    }

    /**
     * Gets the value of the recurrenceMonthlyRelativityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurrenceMonthlyRelativityType() {
        return recurrenceMonthlyRelativityType;
    }

    /**
     * Sets the value of the recurrenceMonthlyRelativityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurrenceMonthlyRelativityType(String value) {
        this.recurrenceMonthlyRelativityType = value;
    }

    /**
     * Gets the value of the recurrenceMonthlyRelativityDayType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurrenceMonthlyRelativityDayType() {
        return recurrenceMonthlyRelativityDayType;
    }

    /**
     * Sets the value of the recurrenceMonthlyRelativityDayType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurrenceMonthlyRelativityDayType(String value) {
        this.recurrenceMonthlyRelativityDayType = value;
    }

    /**
     * Gets the value of the recurrenceMonthlyRelativityWeekType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurrenceMonthlyRelativityWeekType() {
        return recurrenceMonthlyRelativityWeekType;
    }

    /**
     * Sets the value of the recurrenceMonthlyRelativityWeekType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurrenceMonthlyRelativityWeekType(String value) {
        this.recurrenceMonthlyRelativityWeekType = value;
    }

    /**
     * Gets the value of the recurrenceMonthlyRelativityWeekDayValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecurrenceMonthlyRelativityWeekDayValue() {
        return recurrenceMonthlyRelativityWeekDayValue;
    }

    /**
     * Sets the value of the recurrenceMonthlyRelativityWeekDayValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecurrenceMonthlyRelativityWeekDayValue(Integer value) {
        this.recurrenceMonthlyRelativityWeekDayValue = value;
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
