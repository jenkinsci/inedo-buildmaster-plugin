
package com.inedo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Release_Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PromoteBuild_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartExecution_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecutionStart_Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Requested_Build_Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BuildVariables_Xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PromotionVariables_Xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecutionVariables_Xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BuildImporter_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "releaseNumber",
    "promoteBuildIndicator",
    "startExecutionIndicator",
    "executionStartDate",
    "requestedBuildNumber",
    "buildVariablesXml",
    "promotionVariablesXml",
    "executionVariablesXml",
    "buildImporterConfiguration"
})
@XmlRootElement(name = "Builds_CreateBuild")
public class BuildsCreateBuild {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Release_Number")
    protected String releaseNumber;
    @XmlElement(name = "PromoteBuild_Indicator")
    protected String promoteBuildIndicator;
    @XmlElement(name = "StartExecution_Indicator")
    protected String startExecutionIndicator;
    @XmlElement(name = "ExecutionStart_Date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionStartDate;
    @XmlElement(name = "Requested_Build_Number")
    protected String requestedBuildNumber;
    @XmlElement(name = "BuildVariables_Xml")
    protected String buildVariablesXml;
    @XmlElement(name = "PromotionVariables_Xml")
    protected String promotionVariablesXml;
    @XmlElement(name = "ExecutionVariables_Xml")
    protected String executionVariablesXml;
    @XmlElement(name = "BuildImporter_Configuration")
    protected String buildImporterConfiguration;

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
     * Gets the value of the releaseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseNumber() {
        return releaseNumber;
    }

    /**
     * Sets the value of the releaseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseNumber(String value) {
        this.releaseNumber = value;
    }

    /**
     * Gets the value of the promoteBuildIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoteBuildIndicator() {
        return promoteBuildIndicator;
    }

    /**
     * Sets the value of the promoteBuildIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoteBuildIndicator(String value) {
        this.promoteBuildIndicator = value;
    }

    /**
     * Gets the value of the startExecutionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartExecutionIndicator() {
        return startExecutionIndicator;
    }

    /**
     * Sets the value of the startExecutionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartExecutionIndicator(String value) {
        this.startExecutionIndicator = value;
    }

    /**
     * Gets the value of the executionStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecutionStartDate() {
        return executionStartDate;
    }

    /**
     * Sets the value of the executionStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecutionStartDate(XMLGregorianCalendar value) {
        this.executionStartDate = value;
    }

    /**
     * Gets the value of the requestedBuildNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedBuildNumber() {
        return requestedBuildNumber;
    }

    /**
     * Sets the value of the requestedBuildNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedBuildNumber(String value) {
        this.requestedBuildNumber = value;
    }

    /**
     * Gets the value of the buildVariablesXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildVariablesXml() {
        return buildVariablesXml;
    }

    /**
     * Sets the value of the buildVariablesXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildVariablesXml(String value) {
        this.buildVariablesXml = value;
    }

    /**
     * Gets the value of the promotionVariablesXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionVariablesXml() {
        return promotionVariablesXml;
    }

    /**
     * Sets the value of the promotionVariablesXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionVariablesXml(String value) {
        this.promotionVariablesXml = value;
    }

    /**
     * Gets the value of the executionVariablesXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionVariablesXml() {
        return executionVariablesXml;
    }

    /**
     * Sets the value of the executionVariablesXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionVariablesXml(String value) {
        this.executionVariablesXml = value;
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

}
