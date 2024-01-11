"use client"

import { ParamsId } from "@/app/models/entity/pathId";
import { findSupplierId, updateSupplier } from "@/app/models/services/supplierApi";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

function Update({ params }: ParamsId) {

    const { id }: any = params;

    const [ids, setId] = useState<string>(id);
    const [cedula, setCedula] = useState<string>();
    const [nombre, setNombre] = useState<string>();
    const [apellido, setApellido] = useState<string>();
    const [servicio, setServicio] = useState<string>();
    const [telefono, setTelefono] = useState<string>();
    const [contrato, setContrato] = useState<string>();

    const [temp, setTemp] = useState<boolean>(false);

    const ruta = useRouter();

    useEffect(
        () => {
            data();
        }, []
    );

    const data = async () => {

        try {
            let response = await findSupplierId(id);
            let json;
            if (response?.status === 200) {
                json = await response.json();
                console.log(json)
                setId(json.data.id);
                setCedula(json.data.cedula);
                setNombre(json.data.nombre);
                setApellido(json.data.apellido);
                setServicio(json.data.service);
                setTelefono(json.data.telefono);
                setContrato(json.data.contrato);
                setTemp(true);
            }
            if (response?.status === 404) {
                alert("data incorrect");
                ruta.push('/');
            }
        } catch (error) {

        }
    }

    const updateSupplierServer = async (envent: any) => {
        envent.preventDefault();
        let data = JSON.stringify({
            id: ids,
            cedula,
            nombre,
            apellido,
            service: servicio,
            telefono,
            contrato
        });

        try {
            let response = await updateSupplier(data, id);
            if (response?.status === 200) {
                alert("Proveedor actualizado correctamente");
                ruta.push("/");
            }
            if (response?.status === 400) {
                alert("Proveedor no registrado en el sistema")
            }
            if (response?.status === 428) {
                alert("Ingresar datos")
            }

        } catch (error) {

        }
    }
    return (
        <>
            {temp
                ?
                <>
                    <div className="flex justify-center p-4 mb-1 bg-blue-700 font-sans">
                        <Link href={"/"} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300   uppercase px-8 p-2 mr-1 border-4 border-double bg-blue-500 shadow-lg shadow-blue-500/50">Inicio</Link>
                    </div>
                    <div className="pt-20">
                        <form onSubmit={updateSupplierServer}>
                            <div className="grid justify-items-stretch p-4 font-sans border-8 border-sky-500  sm:mx-96 bg-white">
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="id">ID</label>
                                    <div>
                                        <input onChange={(e) => setId(e.target.value)} type="number" placeholder="1" value={ids} disabled className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="cedula">CEDULA</label>
                                    <div>
                                        <input onChange={(e) => setCedula(e.target.value)} autoFocus type="number" placeholder="14456387" value={cedula} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="nombre">NOMBRE</label>
                                    <div>
                                        <input onChange={(e) => setNombre(e.target.value)} type="text" placeholder="Natalia" value={nombre} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="apellido">APELLIDO</label>
                                    <div>
                                        <input onChange={(e) => setApellido(e.target.value)} type="text" placeholder="Salazar" value={apellido} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="servicio">SERVICIO</label>
                                    <div>
                                        <select onChange={(e) => setServicio(e.target.value)} name="service" id="service" required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                                            <option >{servicio}</option>
                                            <option value="transporte">Transporte</option>
                                            <option value="seguridad">Seguridad</option>
                                        </select> </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="telefono">TELÉFONO</label>
                                    <div>
                                        <input onChange={(e) => setTelefono(e.target.value)} type="text" placeholder="+57 3184520045" value={telefono} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="contrato">CONTRATO</label>
                                    <select onChange={(e) => setContrato(e.target.value)} name="contrato" id="contrato" required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">

                                        {contrato === "1" ?
                                            <option >TIPO A</option> : <></>
                                        }
                                        {contrato === "2" ?
                                            <option >TIPO B</option> : <></>
                                        }
                                        {contrato === "3" ?
                                            <option>TIPO C</option> : <></>
                                        }

                                        <option value="1">TIPO A</option>
                                        <option value="2">TIPO B</option>
                                        <option value="3">TIPO C</option>
                                    </select>
                                </div>
                                <div className="justify-self-center m-4">
                                    <button className="transition ease-in-out delay-150  hover:-translate-y-1 hover:scale-110 hover:bg-blue-500 duration-300 p-2  bg-cyan-500 shadow-lg shadow-cyan-500/50">Actualizar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </>
                :
                <></>
            }
        </>
    );

}

export default Update;