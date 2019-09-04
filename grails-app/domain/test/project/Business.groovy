package test.project
/* A business sells its accounts receivable (i.e., Bills) at a discount.[ */
class Business {

    String name
    String email
    String enterpriseName
    Double monthlyRevenue

    static constraints = {
        name maxSize: 50
        email email: true
        enterpriseName maxSize: 50
        monthlyRevenue range: 1000..10000000
    }
}
