
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
 *         &lt;element name="Script_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Success_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Log_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Execution_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Provider_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ScriptExecution_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "scriptId",
    "successIndicator",
    "logText",
    "executionId",
    "providerId",
    "scriptExecutionId"
})
@XmlRootElement(name = "DatabaseChangeScripts_LogExecution")
public class DatabaseChangeScriptsLogExecution {

    protected String apiKey;
    @XmlElement(name = "Script_Id", required = true, type = Integer.class, nillable = true)
    protected Integer scriptId;
    @XmlElement(name = "Success_Indicator")
    protected String successIndicator;
    @XmlElement(name = "Log_Text")
    protected String logText;
    @XmlElement(name = "Execution_Id", required = true, type = Integer.class, nillable = true)
    protected Integer executionId;
    @XmlElement(name = "Provider_Id", required = true, type = Integer.class, nillable = true)
    protected Integer providerId;
    @XmlElement(name = "ScriptExecution_Id", required = true, type = Integer.class, nillable = true)
    protected Integer scriptExecutionId;

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
     * Gets the value of the scriptId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getScriptId() {
        return scriptId;
    }

    /**
     * Sets the value of the scriptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setScriptId(Integer value) {
        this.scriptId = value;
    }

    /**
     * Gets the value of the successIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessIndicator() {
        return successIndicator;
    }

    /**
     * Sets the value of the successIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessIndicator(String value) {
        this.successIndicator = value;
    }

    /**
     * Gets the value of the logText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogText() {
        return logText;
    }

    /**
     * Sets the value of the logText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogText(String value) {
        this.logText = value;
    }

    /**
     * Gets the value of the executionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExecutionId() {
        return executionId;
    }

    /**
     * Sets the value of the executionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExecutionId(Integer value) {
        this.executionId = value;
    }

    /**
     * Gets the value of the providerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Sets the value of the providerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProviderId(Integer value) {
        this.providerId = value;
    }

    /**
     * Gets the value of the scriptExecutionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getScriptExecutionId() {
        return scriptExecutionId;
    }

    /**
     * Sets the value of the scriptExecutionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setScriptExecutionId(Integer value) {
        this.scriptExecutionId = value;
    }

}
