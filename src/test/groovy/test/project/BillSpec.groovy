package test.project

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class BillSpec extends Specification implements DomainUnitTest<Bill> {

    BaseRate baseRate = new BaseRate(percentage: 1.65, minRevenue: 1000, maxRevenue: 20000)

    Business business = new Business(baseRate: baseRate, name: "André Cunha", email: "aandrecunhas@gmail.com", enterpriseName: "André Móveis", monthlyRevenue: 10000)

    Bill bill = new Bill()

    def setup() {

    }

    def cleanup() {
    }

    void "Test Bill attributes"() {

        when: "Set values in Bill"
        bill.baseRatePercentage = baseRatePercentage
        bill.originalValue = originalValue
        bill.daysToPay = daysToPay
        bill.business = business
        bill.save()

        then: "Bill should have no errors"
        !bill.hasErrors()

        where:
        baseRatePercentage | originalValue | daysToPay
        1.65 | 1500 | 28
        1.65 | 2000 | 35
        1.65 | 4000 | 61
        1.65 | 2300 | 15
    }

    void "Test if wrong attributes for Bill class will fail" () {

        when: "Set wrong values in Bill"
        bill.baseRatePercentage = baseRatePercentage
        bill.originalValue = originalValue
        bill.daysToPay = daysToPay
        bill.business = business
        bill.save()

        then: "Bill should have some errors"
        bill.errors[wrongAttribute]?.codes?.contains(expectedError)

        where:
        baseRatePercentage | originalValue | daysToPay | wrongAttribute | expectedError
        150 | 1500 | 28 | 'baseRatePercentage' | 'bill.baseRatePercentage.range.error'
        1.65 | 50 | 35 | 'originalValue' | 'bill.originalValue.range.error'
        1.65 | 600 | 30000000 | 'daysToPay' | 'bill.daysToPay.range.error'

    }

    void "verify if factoringRate, baseRatePercentage and daysToPay are consistent" () {

        when: "Set values in Bill"
        bill.baseRatePercentage = baseRatePercentage
        bill.originalValue = 1500
        bill.daysToPay = daysToPay
        bill.business = business
        bill.save()

        then: "factoring rate should increase each 10 days by baseRate" //Math.ceil(bill.daysToPay / 10) * baseRate
        bill.factoringRate == expectedFactoringRate

        where:
        baseRatePercentage | daysToPay | expectedFactoringRate
        1.65 | 11 | 3.30
        1.65 | 28 | 4.95
        1.99 | 35 | 7.96
    }

    void "verify if amount receivable and discount are consistent" () {

        when: "Set values in Bill"
        bill.baseRatePercentage = baseRatePercentage
        bill.originalValue = originalValue
        bill.daysToPay = daysToPay
        bill.business = business
        bill.save()

        then: "Amount receivable plus discount are equal to originalValue"
        bill.amountReceivable + bill.discount == bill.originalValue

        where:
        baseRatePercentage | daysToPay | originalValue
        1.65 | 11 | 2000
        1.65 | 28 | 1500
        1.99 | 35 | 5000
    }
}
