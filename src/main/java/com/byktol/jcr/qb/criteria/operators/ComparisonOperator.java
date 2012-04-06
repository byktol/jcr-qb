/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.byktol.jcr.qb.criteria.operators;

/**
 * The comparison operators used when comparing values in the query. These
 * operators may be different when using XPath or JCR-SQL2.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public enum ComparisonOperator
{

  /** "Equals" comparison operator. */
  EQUALS("="),
  /** "Greater than" comparison operator. */
  GREATER_THAN(">"),
  /** "Greater than or equals to" comparison operator. */
  GREATER_THAN_OR_EQUALS(">="),
  /** "Less than" comparison operator. */
  LESS_THAN("<"),
  /** "Less than or equals to" comparison operator. */
  LESS_THAN_OR_EQUALS("<="),
  /** "Not equals" comparison operator. */
  NOT_EQUALS("!=", "<>");

  /** The XPath comparison symbol. */
  private final String xpath;
  /** The JCR-SQL2 comparison symbol. */
  private final String sql2;

  /**
   * Private constructor.
   *
   * @param xpath
   *          The XPath comparison symbol.
   */
  private ComparisonOperator(final String xpath)
  {
    this(xpath, xpath);
  }

  /**
   * Private constructor.
   *
   * @param xpath
   *          The XPath comparison symbol.
   * @param sql2
   *          The JCR-SQL2 comparison symbol.
   */
  private ComparisonOperator(final String xpath, final String sql2)
  {
    this.xpath = xpath;
    this.sql2 = sql2;
  }

  /**
   * @return the operator's JCR-SQL2 operator
   */
  public String toSql2()
  {
    return this.sql2;
  }

  /**
   * @return the operator's XPath equivalent.
   */
  public String toXpath()
  {
    return this.xpath;
  }

  /**
   * Override enum's default {@link #toString()} to return the XPath equivalent
   * of the operator. If you want the JCR-SQL2 equivalent, use {@link #toSql2()}
   *
   * @see #toXpath() Because it's the default.
   * @return The default representation of the operator.
   */
  @Override
  public String toString()
  {
    return toXpath();
  }
}
