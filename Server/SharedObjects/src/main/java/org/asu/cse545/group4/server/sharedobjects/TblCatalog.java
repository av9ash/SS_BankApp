package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_catalog")
public class TblCatalog
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  @AttributeOverrides({@javax.persistence.AttributeOverride(name="catalogCategoryId", column=@Column(name="catalog_category_id", nullable=false)), @javax.persistence.AttributeOverride(name="catalogCategoryType", column=@Column(name="catalog_category_type", nullable=false))})
  private TblCatalogId id;
  @Column(name="catalog_category_description")
  private String catalogCategoryDescription;
  
  public TblCatalog() {}
  
  public TblCatalog(TblCatalogId id)
  {
    this.id = id;
  }
  
  public TblCatalog(TblCatalogId id, String catalogCategoryDescription)
  {
    this.id = id;
    this.catalogCategoryDescription = catalogCategoryDescription;
  }
  
  public TblCatalogId getId()
  {
    return this.id;
  }
  
  public void setId(TblCatalogId id)
  {
    this.id = id;
  }
  
  public String getCatalogCategoryDescription()
  {
    return this.catalogCategoryDescription;
  }
  
  public void setCatalogCategoryDescription(String catalogCategoryDescription)
  {
    this.catalogCategoryDescription = catalogCategoryDescription;
  }
}
