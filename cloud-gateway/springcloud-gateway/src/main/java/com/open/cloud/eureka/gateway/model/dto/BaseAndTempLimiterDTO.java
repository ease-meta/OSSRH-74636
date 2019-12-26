package com.open.cloud.eureka.gateway.model.dto;


import java.util.ArrayList;
import java.util.List;

public class BaseAndTempLimiterDTO {
	private List<LimiterDefinitionDTO> baseLimiter;
	private List<LimiterDefinitionDTO> tempLimiter;

	public BaseAndTempLimiterDTO() {
		this.baseLimiter = new ArrayList<LimiterDefinitionDTO>();
		this.tempLimiter = new ArrayList<LimiterDefinitionDTO>();
	}

	public List<LimiterDefinitionDTO> getBaseLimiter() {
		return this.baseLimiter;
	}

	public List<LimiterDefinitionDTO> getTempLimiter() {
		return this.tempLimiter;
	}

	public void setBaseLimiter(final List<LimiterDefinitionDTO> baseLimiter) {
		this.baseLimiter = baseLimiter;
	}

	public void setTempLimiter(final List<LimiterDefinitionDTO> tempLimiter) {
		this.tempLimiter = tempLimiter;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof BaseAndTempLimiterDTO)) {
			return false;
		}
		final BaseAndTempLimiterDTO other = (BaseAndTempLimiterDTO) o;
		if (!other.canEqual(this)) {
			return false;
		}
		final Object this$baseLimiter = this.getBaseLimiter();
		final Object other$baseLimiter = other.getBaseLimiter();
		Label_0065:
		{
			if (this$baseLimiter == null) {
				if (other$baseLimiter == null) {
					break Label_0065;
				}
			} else if (this$baseLimiter.equals(other$baseLimiter)) {
				break Label_0065;
			}
			return false;
		}
		final Object this$tempLimiter = this.getTempLimiter();
		final Object other$tempLimiter = other.getTempLimiter();
		if (this$tempLimiter == null) {
			if (other$tempLimiter == null) {
				return true;
			}
		} else if (this$tempLimiter.equals(other$tempLimiter)) {
			return true;
		}
		return false;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BaseAndTempLimiterDTO;
	}

	@Override
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $baseLimiter = this.getBaseLimiter();
		result = result * 59 + (($baseLimiter == null) ? 43 : $baseLimiter.hashCode());
		final Object $tempLimiter = this.getTempLimiter();
		result = result * 59 + (($tempLimiter == null) ? 43 : $tempLimiter.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "BaseAndTempLimiterDTO(baseLimiter=" + this.getBaseLimiter() + ", tempLimiter=" + this.getTempLimiter() + ")";
	}
}
