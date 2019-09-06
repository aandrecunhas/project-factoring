package test.project
/* A business sells its accounts receivable (i.e., Bills) at a discount. */
class Business {

    String name
    String email
    String enterpriseName
    Double monthlyRevenue
    BaseRate baseRate

    static constraints = {
        name maxSize: 50
        email email: true
        enterpriseName maxSize: 50
        monthlyRevenue range: 1000..10000000
        baseRate validator: { val, obj, errors ->
            if(obj.monthlyRevenue > val.maxRevenue) {
                errors.rejectValue('baseRate', 'revenue.tooBig')
                return false
            }

            if(obj.monthlyRevenue < val.minRevenue) {
                errors.rejectValue('baseRate', 'revenue.tooSmall')
                return false
            }

            return true
        }
    }

    void setMonthlyRevenue(Double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue

        BaseRate baseRate = BaseRate.createCriteria().get {
            le('minRevenue', monthlyRevenue)
            ge('maxRevenue', monthlyRevenue)
        }

        if(baseRate) {
            this.baseRate = baseRate
        }
    }
}
