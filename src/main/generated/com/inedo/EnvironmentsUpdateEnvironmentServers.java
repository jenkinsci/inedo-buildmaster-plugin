
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
 *         &lt;element name="ServerIdsInEnvironment_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "serverIdsInEnvironmentCsv"
})
@XmlRootElement(name = "Environments_UpdateEnvironmentServers")
public class EnvironmentsUpdateEnvironmentServers {

    protected String apiKey;
    @XmlElement(name = "Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer environmentId;
    @XmlElement(name = "ServerIdsInEnvironment_Csv")
    protected String serverIdsInEnvironmentCsv;

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
     * Gets the value of the serverIdsInEnvironmentCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerIdsInEnvironmentCsv() {
        return serverIdsInEnvironmentCsv;
    }

    /**
     * Sets the value of the serverIdsInEnvironmentCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerIdsInEnvironmentCsv(String value) {
        this.serverIdsInEnvironmentCsv = value;
    }

}
