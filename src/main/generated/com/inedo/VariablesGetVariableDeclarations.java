
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
 *         &lt;element name="Scope_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Scoped_Application_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Scoped_Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "scopeCode",
    "scopedApplicationId",
    "scopedEnvironmentId"
})
@XmlRootElement(name = "Variables_GetVariableDeclarations")
public class VariablesGetVariableDeclarations {

    protected String apiKey;
    @XmlElement(name = "Scope_Code")
    protected String scopeCode;
    @XmlElement(name = "Scoped_Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer scopedApplicationId;
    @XmlElement(name = "Scoped_Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer scopedEnvironmentId;

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

}
