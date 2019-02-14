package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TblCatalogId implements Serializable {
	private int catalogCategoryId;
	private int catalogCategoryType;

	public TblCatalogId() {
	}

	public TblCatalogId(int catalogCategoryId, int catalogCategoryType) {
		this.catalogCategoryId = catalogCategoryId;
		this.catalogCategoryType = catalogCategoryType;
	}

	@Column(name = "catalog_category_id", nullable = false)
	public int getCatalogCategoryId() {
		return this.catalogCategoryId;
	}

	public void setCatalogCategoryId(int catalogCategoryId) {
		this.catalogCategoryId = catalogCategoryId;
	}

	@Column(name = "catalog_category_type", nullable = false)
	public int getCatalogCategoryType() {
		return this.catalogCategoryType;
	}

	public void setCatalogCategoryType(int catalogCategoryType) {
		this.catalogCategoryType = catalogCategoryType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof TblCatalogId)) {
			return false;
		}
		TblCatalogId castOther = (TblCatalogId) other;

		return (getCatalogCategoryId() == castOther.getCatalogCategoryId())
				&& (getCatalogCategoryType() == castOther.getCatalogCategoryType());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + getCatalogCategoryId();
		result = 37 * result + getCatalogCategoryType();
		return result;
	}
}
