package test.project

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BillController {

    BillService billService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond billService.list(params), model:[billCount: billService.count()]
    }

    def show(Long id) {
        respond billService.get(id)
    }

    def create() {
        respond new Bill(params)
    }

    def save(Bill bill) {
        if (bill == null) {
            notFound()
            return
        }

        try {
            billService.save(bill)
        } catch (ValidationException e) {
            respond bill.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bill.label', default: 'Bill'), bill.id])
                redirect bill
            }
            '*' { respond bill, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond billService.get(id)
    }

    def update(Bill bill) {
        if (bill == null) {
            notFound()
            return
        }

        try {
            billService.save(bill)
        } catch (ValidationException e) {
            respond bill.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bill.label', default: 'Bill'), bill.id])
                redirect bill
            }
            '*'{ respond bill, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        billService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bill.label', default: 'Bill'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bill.label', default: 'Bill'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
