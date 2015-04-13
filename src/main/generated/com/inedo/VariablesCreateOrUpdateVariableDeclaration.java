
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
 *         &lt;element name="Variable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Scope_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Scoped_Application_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Scoped_Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Required_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Variable_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DefaultValue_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sensitive_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "variableName",
    "scopeCode",
    "scopedApplicationId",
    "scopedEnvironmentId",
    "requiredIndicator",
    "variableConfiguration",
    "defaultValueText",
    "sensitiveIndicator"
})
@XmlRootElement(name = "Variables_CreateOrUpdateVariableDeclaration")
public class VariablesCreateOrUpdateVariableDeclaration {

    protected String apiKey;
    @XmlElement(name = "Variable_Name")
    protected String variableName;
    @XmlElement(name = "Scope_Code")
    protected String scopeCode;
    @XmlElement(name = "Scoped_Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer scopedApplicationId;
    @XmlElement(name = "Scoped_Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer scopedEnvironmentId;
    @XmlElement(name = "Required_Indicator")
    protected String requiredIndicator;
    @XmlElement(name = "Variable_Configuration")
    protected String variableConfiguration;
    @XmlElement(name = "DefaultValue_Text")
    protected String defaultValueText;
    @XmlElement(name = "Sensitive_Indicator")
    protected String sensitiveIndicator;

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
     * Gets the value of the variableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Sets the value of the variableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariableName(String value) {
        this.variableName = value;
    }

    /**
     * Gets the value of the scopeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScopeCode() {
        return scopeCode;
    }

    /**
     * Sets the value of the scopeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScopeCode(String value) {
        this.scopeCode = value;
    }

    /**
     * Gets the value of the scopedApplicationId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getScopedApplicationId() {
        return scopedApplicationId;
    }

    /**
     * Sets the value of the scopedApplicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setScopedApplicationId(Integer value) {
        this.scopedApplicationId = value;
    }

    /**
     * Gets the value of the scopedEnvironmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getScopedEnvironmentId() {
        return scopedEnvironmentId;
    }

    /**
     * Sets the value of the scopedEnvironmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setScopedEnvironmentId(Integer value) {
        this.scopedEnvironmentId = value;
    }

    /**
     * Gets the value of the requiredIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiredIndicator() {
        return requiredIndicator;
    }

    /**
     * Sets the value of the requiredIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredIndicator(String value) {
        this.requiredIndicator = value;
    }

    /**
     * Gets the value of the variableConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariableConfiguration() {
        return variableConfiguration;
    }

    /**
     * Sets the value of the variableConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariableConfiguration(String value) {
        this.variableConfiguration = value;
    }

    /**
     * Gets the value of the defaultValueText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValueText() {
        return defaultValueText;
    }

    /**
     * Sets the value of the defaultValueText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValueText(String value) {
        this.defaultValueText = value;
    }

    /**
     * Gets the value of the sensitiveIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensitiveIndicator() {
        return sensitiveIndicator;
    }

    /**
     * Sets the value of the sensitiveIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensitiveIndicator(String value) {
        this.sensitiveIndicator = value;
    }

}
