package test.project
/* This is the predefined basic rate using to calculate the amount receivable of a business */
class BaseRate {

    Double percentage
    Double minRevenue
    Double maxRevenue

    static constraints = {
        percentage nullable: true, range: 0..100
        minRevenue nullable: true, range: 0..10000000, validator: { val, obj, errors ->
            if(val == null) {
                return true
            }

            if(val <= obj.maxRevenue) {
                return true
            } else {
                errors.rejectValue('minRevenue', 'range.toobig')
                return false
            }

        }
        maxRevenue nullable: true, range: 0..10000000, validator: { val, obj, errors ->
            if(val == null) {
                return true
            }

            if(val >= obj.minRevenue) {
                return true
            } else {
                errors.rejectValue('maxRevenue', 'range.toosmall')
                return false
            }

        }
    }
}
