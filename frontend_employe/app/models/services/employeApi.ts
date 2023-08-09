import { headersApi } from "./authorization/authorization";

const API_EMPLOYE = "http://localhost:9000/api/v1/emp";
export const findEmployes = async () => {

    try {
        let resonse = await fetch(API_EMPLOYE + "/employes",
            {
                method: "GET"
            }
        );
        return resonse;
    } catch (error) {

    }
}
/**
 * Method for search employe id
 * @param id 
 * @returns 
 */
export const findEmployeId = async (id: number) => {
    try {
        let resonse = await fetch(API_EMPLOYE + "/employe/" + id, {
            method: "GET"
        });
        return resonse;
    } catch (error) {

    }
}

export const createEmploye = async (data: string) => {
    try {
        let resonse = await fetch(API_EMPLOYE + "/employe", {
            method: "POST",
            headers: headersApi(),
            body: data
        }
        );
        return resonse;
    } catch (error) {

    }
}
export const updateEmploye = async (data: string, id: number) => {
    try {
        let resonse = await fetch(API_EMPLOYE + "/employe/" + id, {
            method: "PUT",
            headers: headersApi(),
            body: data
        });
        return resonse;
    } catch (error) {

    }
}
export const deleteEmploye = async (id: number) => {
    try {
        let resonse = await fetch(API_EMPLOYE + "/employe/" + id, {
            method: "DELETE"
        })
        return resonse;
    } catch (error) {

    }
}