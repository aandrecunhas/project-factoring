package test.project
/* This is the predefined basic rate using to calculate the amount receivable of a business */
class BaseRate {

    Double percentage
    Double minRevenue
    Double maxRevenue

    static constraints = {
        percentage nullable: true, range: 0..100
        minRevenue nullable: true, range: 0..Double.MAX_VALUE
        maxRevenue nullable: true, range: 0..Double.MAX_VALUE
    }
}
