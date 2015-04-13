
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
 *         &lt;element name="Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Principal_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequirementType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Requirement_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequiredInGroup_Count" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "environmentId",
    "principalName",
    "requirementTypeCode",
    "requirementDescription",
    "requiredInGroupCount"
})
@XmlRootElement(name = "Environments_CreateOrUpdateManualRequirement")
public class EnvironmentsCreateOrUpdateManualRequirement {

    protected String apiKey;
    @XmlElement(name = "Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer environmentId;
    @XmlElement(name = "Principal_Name")
    protected String principalName;
    @XmlElement(name = "RequirementType_Code")
    protected String requirementTypeCode;
    @XmlElement(name = "Requirement_Description")
    protected String requirementDescription;
    @XmlElement(name = "RequiredInGroup_Count", required = true, type = Integer.class, nillable = true)
    protected Integer requiredInGroupCount;

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
     * Gets the value of the environmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEnvironmentId() {
        return environmentId;
    }

    /**
     * Sets the value of the environmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEnvironmentId(Integer value) {
        this.environmentId = value;
    }

    /**
     * Gets the value of the principalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalName() {
        return principalName;
    }

    /**
     * Sets the value of the principalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalName(String value) {
        this.principalName = value;
    }

    /**
     * Gets the value of the requirementTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequirementTypeCode() {
        return requirementTypeCode;
    }

    /**
     * Sets the value of the requirementTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequirementTypeCode(String value) {
        this.requirementTypeCode = value;
    }

    /**
     * Gets the value of the requirementDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequirementDescription() {
        return requirementDescription;
    }

    /**
     * Sets the value of the requirementDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequirementDescription(String value) {
        this.requirementDescription = value;
    }

    /**
     * Gets the value of the requiredInGroupCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequiredInGroupCount() {
        return requiredInGroupCount;
    }

    /**
     * Sets the value of the requiredInGroupCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequiredInGroupCount(Integer value) {
        this.requiredInGroupCount = value;
    }

}
