
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
 *         &lt;element name="Directory_Provider_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="User_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GroupNames_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "directoryProviderId",
    "userName",
    "groupNamesCsv"
})
@XmlRootElement(name = "Security_IsAuthorizedForAnyTask")
public class SecurityIsAuthorizedForAnyTask {

    protected String apiKey;
    @XmlElement(name = "Directory_Provider_Id", required = true, type = Integer.class, nillable = true)
    protected Integer directoryProviderId;
    @XmlElement(name = "User_Name")
    protected String userName;
    @XmlElement(name = "GroupNames_Csv")
    protected String groupNamesCsv;

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
     * Gets the value of the directoryProviderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectoryProviderId() {
        return directoryProviderId;
    }

    /**
     * Sets the value of the directoryProviderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectoryProviderId(Integer value) {
        this.directoryProviderId = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the groupNamesCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupNamesCsv() {
        return groupNamesCsv;
    }

    /**
     * Sets the value of the groupNamesCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupNamesCsv(String value) {
        this.groupNamesCsv = value;
    }

}
