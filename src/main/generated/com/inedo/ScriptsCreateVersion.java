
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
 *         &lt;element name="Script_Bytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "scriptBytes"
})
@XmlRootElement(name = "Scripts_CreateVersion")
public class ScriptsCreateVersion {

    protected String apiKey;
    @XmlElement(name = "Script_Id", required = true, type = Integer.class, nillable = true)
    protected Integer scriptId;
    @XmlElement(name = "Script_Bytes")
    protected byte[] scriptBytes;

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
     * Gets the value of the scriptBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getScriptBytes() {
        return scriptBytes;
    }

    /**
     * Sets the value of the scriptBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setScriptBytes(byte[] value) {
        this.scriptBytes = value;
    }

}
