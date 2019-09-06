package test.project

import grails.gorm.services.Service

@Service(Bill)
interface BillService {

    Bill get(Serializable id)

    List<Bill> list(Map args)

    Long count()

    void delete(Serializable id)

    Bill save(Bill bill)

}