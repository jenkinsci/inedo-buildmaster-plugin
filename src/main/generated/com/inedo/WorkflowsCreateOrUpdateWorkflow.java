
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
 *         &lt;element name="Workflow_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AutoIncrementRelease_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AutoDeployRelease_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllowOutOfSequencePromotions_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AutoCancelReleases_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Color_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Build_AutoPromote_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllowLocalChanges_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "workflowName",
    "activeIndicator",
    "autoIncrementReleaseIndicator",
    "autoDeployReleaseIndicator",
    "allowOutOfSequencePromotionsIndicator",
    "autoCancelReleasesIndicator",
    "colorCode",
    "buildAutoPromoteIndicator",
    "allowLocalChangesIndicator"
})
@XmlRootElement(name = "Workflows_CreateOrUpdateWorkflow")
public class WorkflowsCreateOrUpdateWorkflow {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Workflow_Name")
    protected String workflowName;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;
    @XmlElement(name = "AutoIncrementRelease_Indicator")
    protected String autoIncrementReleaseIndicator;
    @XmlElement(name = "AutoDeployRelease_Indicator")
    protected String autoDeployReleaseIndicator;
    @XmlElement(name = "AllowOutOfSequencePromotions_Indicator")
    protected String allowOutOfSequencePromotionsIndicator;
    @XmlElement(name = "AutoCancelReleases_Indicator")
    protected String autoCancelReleasesIndicator;
    @XmlElement(name = "Color_Code")
    protected String colorCode;
    @XmlElement(name = "Build_AutoPromote_Indicator")
    protected String buildAutoPromoteIndicator;
    @XmlElement(name = "AllowLocalChanges_Indicator")
    protected String allowLocalChangesIndicator;

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
     * Gets the value of the workflowName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowName() {
        return workflowName;
    }

    /**
     * Sets the value of the workflowName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowName(String value) {
        this.workflowName = value;
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

    /**
     * Gets the value of the autoIncrementReleaseIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoIncrementReleaseIndicator() {
        return autoIncrementReleaseIndicator;
    }

    /**
     * Sets the value of the autoIncrementReleaseIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoIncrementReleaseIndicator(String value) {
        this.autoIncrementReleaseIndicator = value;
    }

    /**
     * Gets the value of the autoDeployReleaseIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoDeployReleaseIndicator() {
        return autoDeployReleaseIndicator;
    }

    /**
     * Sets the value of the autoDeployReleaseIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoDeployReleaseIndicator(String value) {
        this.autoDeployReleaseIndicator = value;
    }

    /**
     * Gets the value of the allowOutOfSequencePromotionsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowOutOfSequencePromotionsIndicator() {
        return allowOutOfSequencePromotionsIndicator;
    }

    /**
     * Sets the value of the allowOutOfSequencePromotionsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowOutOfSequencePromotionsIndicator(String value) {
        this.allowOutOfSequencePromotionsIndicator = value;
    }

    /**
     * Gets the value of the autoCancelReleasesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoCancelReleasesIndicator() {
        return autoCancelReleasesIndicator;
    }

    /**
     * Sets the value of the autoCancelReleasesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoCancelReleasesIndicator(String value) {
        this.autoCancelReleasesIndicator = value;
    }

    /**
     * Gets the value of the colorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColorCode() {
        return colorCode;
    }

    /**
     * Sets the value of the colorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColorCode(String value) {
        this.colorCode = value;
    }

    /**
     * Gets the value of the buildAutoPromoteIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildAutoPromoteIndicator() {
        return buildAutoPromoteIndicator;
    }

    /**
     * Sets the value of the buildAutoPromoteIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildAutoPromoteIndicator(String value) {
        this.buildAutoPromoteIndicator = value;
    }

    /**
     * Gets the value of the allowLocalChangesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowLocalChangesIndicator() {
        return allowLocalChangesIndicator;
    }

    /**
     * Sets the value of the allowLocalChangesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowLocalChangesIndicator(String value) {
        this.allowLocalChangesIndicator = value;
    }

}
