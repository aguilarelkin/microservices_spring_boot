import { headersApi } from "./authorization/authorization";

const API_SUPPLIER = "http://localhost:9001/api/v1/sup";

export const findSuppliers = async () => {
    try {
        let response = fetch(API_SUPPLIER + '/suppliers', {
            method: 'GET'
        });

        return response;
    } catch (error) {

    }

}
export const findSupplierId = async (id: number) => {
    try {
        let response = fetch(API_SUPPLIER + '/supplier/' + id, {
            method: 'GET'
        });

        return response;
    } catch (error) {

    }
}
export const createSupplier = async (data: string) => {
    try {
        let response = fetch(API_SUPPLIER + '/supplier', {
            method: 'POST',
            headers: headersApi(),
            body: data
        });

        return response;
    } catch (error) {

    }
}
export const updateSupplier = async (data: string, id: number) => {
    try {
        let response = fetch(API_SUPPLIER + '/supplier/' + id, {
            method: 'PUT',
            headers: headersApi(),
            body: data
        });

        return response;
    } catch (error) {

    }
}
export const deleteSupplier = async (id: number) => {
    try {
        let response = fetch(API_SUPPLIER + '/supplier/' + id, {
            method: 'DELETE'
        });

        return response;
    } catch (error) {

    }
}