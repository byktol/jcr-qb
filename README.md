Criteria criteria = session.createCriteria();
criteria.add(Restrictions.eq("prop1", "value1"));
String query = criteria.buildQuery(); // builds the query
Generates:
/jcr:root//element(*)[@prop1 = "value1"]

Criteria criteria = session.createCriteria();
criteria.add(Restrictions.ge("prop1", 1)); // greater or equals to
criteria.add(Restrictions.lt("prop1", 99)); // less than
criteria.buildQuery(); // returns a string
Generates:
/jcr:root//element(*)[@prop1 >= 1 and @prop1 < 99]

Criteria criteria = session.createCriteria();
criteria.add(Restrictions.gt("published", Calendar.getInstance()); // greater than
criteria.buildQuery(); // returns a string
Generates:
/jcr:root//element(*)[@published > xs:dateTime('2012-02-02T10:00:00.000-05:00')]

Criteria criteria = session.createCriteria();
criteria.setPath("/content/nymag");
criteria.setNodeType("cq:PageContent");
criteria.add(Restrictions.exists("description"));
criteria.add(
  Restrictions.or(
    Restrictions.contains("title", "RFC"),
    Restrictions.contains("title", "refactor")
  )
);
criteria.addOrder(Order.desc("published"));
criteria.addOrder(Order.asc("topstorystatus"));
criteria.buildQuery(); // returns a string
Generates:
/jcr:root/content/nymag//element(*,cq:PageContent)
  [@description and (jcr:contains(@title, 'RFC') or jcr:contains(@title, 'refactor'))]
  order by @published descending, @topstorystatus ascending