package test.project

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BusinessController {

    BusinessService businessService
    BillService billService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond businessService.list(params), model:[businessCount: businessService.count()]
    }

    def show(Long id) {
        respond businessService.get(id)
    }

    def create() {
        respond new Business(params)
    }

    def save(Business business) {
        if (business == null) {
            notFound()
            return
        }

        try {
            businessService.save(business)
        } catch (ValidationException e) {
            respond business.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'business.label', default: 'Business'), business.id])
                redirect business
            }
            '*' { respond business, [status: CREATED] }
        }
    }

    def saveBill() {

        Bill bill = new Bill()
        bill.originalValue = Double.parseDouble(params.originalValue.replace(".", "").replace(",", "."))
        bill.business = Business.get(params.business.id)
        bill.daysToPay = Integer.parseInt(params.daysToPay)
        bill.baseRatePercentage = bill.business.baseRate.percentage

        try {
            billService.save(bill)
        } catch (ValidationException e) {
            respond bill.errors, view:'show'
            return
        }

        redirect(action: "show", id: params.business.id)

    }

    def edit(Long id) {
        respond businessService.get(id)
    }

    def update(Business business) {
        if (business == null) {
            notFound()
            return
        }

        try {
            businessService.save(business)
        } catch (ValidationException e) {
            respond business.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'business.label', default: 'Business'), business.id])
                redirect business
            }
            '*'{ respond business, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        businessService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'business.label', default: 'Business'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'business.label', default: 'Business'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
