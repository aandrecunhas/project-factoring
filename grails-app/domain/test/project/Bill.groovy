package test.project
/* Bill of a business calculate (accounts receivable) */
class Bill {

    Double baseRatePercentage
    Double originalValue
    Integer daysToPay
    Business business

    static transients = ['factoringRate', 'amountReceivable', 'discount']

    static constraints = {
        baseRatePercentage range: 0..100
        originalValue range: 500..10000000
        daysToPay range: 10..1000
    }

    Double getFactoringRate() {
        Double factoringRateBeforeFixing =  Math.ceil(this.daysToPay / 10) * baseRatePercentage
        return Math.round(factoringRateBeforeFixing * 100) / 100
    }

    def getAmountReceivable() {
        return this.originalValue - this.discount
    }

    def getDiscount() {
        return this.factoringRate / 100 * this.originalValue
    }
}
