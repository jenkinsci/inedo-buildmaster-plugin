
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
 *         &lt;element name="Build_Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecutionStart_Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="OverrideApprovals_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="From_Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="To_Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PromoteFor_Release_Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContinueOnFailedPromote_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "buildNumber",
    "executionStartDate",
    "overrideApprovalsIndicator",
    "fromEnvironmentId",
    "toEnvironmentId",
    "promoteForReleaseNumber",
    "continueOnFailedPromoteIndicator"
})
@XmlRootElement(name = "Builds_PromoteDependents")
public class BuildsPromoteDependents {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Release_Number")
    protected String releaseNumber;
    @XmlElement(name = "Build_Number")
    protected String buildNumber;
    @XmlElement(name = "ExecutionStart_Date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionStartDate;
    @XmlElement(name = "OverrideApprovals_Indicator")
    protected String overrideApprovalsIndicator;
    @XmlElement(name = "From_Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer fromEnvironmentId;
    @XmlElement(name = "To_Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer toEnvironmentId;
    @XmlElement(name = "PromoteFor_Release_Number")
    protected String promoteForReleaseNumber;
    @XmlElement(name = "ContinueOnFailedPromote_Indicator")
    protected String continueOnFailedPromoteIndicator;

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
     * Gets the value of the buildNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildNumber() {
        return buildNumber;
    }

    /**
     * Sets the value of the buildNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildNumber(String value) {
        this.buildNumber = value;
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
     * Gets the value of the overrideApprovalsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverrideApprovalsIndicator() {
        return overrideApprovalsIndicator;
    }

    /**
     * Sets the value of the overrideApprovalsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverrideApprovalsIndicator(String value) {
        this.overrideApprovalsIndicator = value;
    }

    /**
     * Gets the value of the fromEnvironmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFromEnvironmentId() {
        return fromEnvironmentId;
    }

    /**
     * Sets the value of the fromEnvironmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFromEnvironmentId(Integer value) {
        this.fromEnvironmentId = value;
    }

    /**
     * Gets the value of the toEnvironmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getToEnvironmentId() {
        return toEnvironmentId;
    }

    /**
     * Sets the value of the toEnvironmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setToEnvironmentId(Integer value) {
        this.toEnvironmentId = value;
    }

    /**
     * Gets the value of the promoteForReleaseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoteForReleaseNumber() {
        return promoteForReleaseNumber;
    }

    /**
     * Sets the value of the promoteForReleaseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoteForReleaseNumber(String value) {
        this.promoteForReleaseNumber = value;
    }

    /**
     * Gets the value of the continueOnFailedPromoteIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContinueOnFailedPromoteIndicator() {
        return continueOnFailedPromoteIndicator;
    }

    /**
     * Sets the value of the continueOnFailedPromoteIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContinueOnFailedPromoteIndicator(String value) {
        this.continueOnFailedPromoteIndicator = value;
    }

}
