package com.linkedin.thirdeye.heatmap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeatMapCell
{
  private String dimensionValue;
  private Number current;
  private Number baseline;
  private double ratio;
  private double alpha;
  private String color;

  public HeatMapCell() {}

  public HeatMapCell(String dimensionValue,
                     Number current,
                     Number baseline,
                     double ratio,
                     double alpha,
                     String color)
  {
    this.dimensionValue = dimensionValue;
    this.current = current;
    this.baseline = baseline;
    this.ratio = ratio;
    this.alpha = alpha;
    this.color = color;
  }

  @JsonProperty
  public String getDimensionValue()
  {
    return dimensionValue;
  }

  @JsonProperty
  public Number getCurrent()
  {
    return current;
  }

  @JsonProperty
  public Number getBaseline()
  {
    return baseline;
  }

  @JsonProperty
  public double getRatio()
  {
    return ratio;
  }

  @JsonProperty
  public double getAlpha()
  {
    return alpha;
  }

  @JsonProperty
  public String getColor()
  {
    return color;
  }
}
