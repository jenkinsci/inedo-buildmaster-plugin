
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
 *         &lt;element name="Execution_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ConfigurationFile_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Instance_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Version_Number" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DeployedTo_Path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeployedBy_User_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "executionId",
    "configurationFileId",
    "instanceName",
    "versionNumber",
    "serverId",
    "deployedToPath",
    "deployedByUserName"
})
@XmlRootElement(name = "ConfigurationFiles_DeployConfigurationFile")
public class ConfigurationFilesDeployConfigurationFile {

    protected String apiKey;
    @XmlElement(name = "Execution_Id", required = true, type = Integer.class, nillable = true)
    protected Integer executionId;
    @XmlElement(name = "ConfigurationFile_Id", required = true, type = Integer.class, nillable = true)
    protected Integer configurationFileId;
    @XmlElement(name = "Instance_Name")
    protected String instanceName;
    @XmlElement(name = "Version_Number", required = true, type = Integer.class, nillable = true)
    protected Integer versionNumber;
    @XmlElement(name = "Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer serverId;
    @XmlElement(name = "DeployedTo_Path")
    protected String deployedToPath;
    @XmlElement(name = "DeployedBy_User_Name")
    protected String deployedByUserName;

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
     * Gets the value of the configurationFileId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConfigurationFileId() {
        return configurationFileId;
    }

    /**
     * Sets the value of the configurationFileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConfigurationFileId(Integer value) {
        this.configurationFileId = value;
    }

    /**
     * Gets the value of the instanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * Sets the value of the instanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceName(String value) {
        this.instanceName = value;
    }

    /**
     * Gets the value of the versionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersionNumber() {
        return versionNumber;
    }

    /**
     * Sets the value of the versionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersionNumber(Integer value) {
        this.versionNumber = value;
    }

    /**
     * Gets the value of the serverId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServerId() {
        return serverId;
    }

    /**
     * Sets the value of the serverId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServerId(Integer value) {
        this.serverId = value;
    }

    /**
     * Gets the value of the deployedToPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployedToPath() {
        return deployedToPath;
    }

    /**
     * Sets the value of the deployedToPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployedToPath(String value) {
        this.deployedToPath = value;
    }

    /**
     * Gets the value of the deployedByUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployedByUserName() {
        return deployedByUserName;
    }

    /**
     * Sets the value of the deployedByUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployedByUserName(String value) {
        this.deployedByUserName = value;
    }

}
