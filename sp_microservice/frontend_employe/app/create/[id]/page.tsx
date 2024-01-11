"use client"

import { ParamsId } from "@/app/models/entity/pathId";
import { findEmployeId, updateEmploye } from "@/app/models/services/employeApi";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

function Update({ params }: ParamsId) {

    const { id }: any = params;

    const [ids, setId] = useState<string>(id);
    const [nombre, setNombre] = useState<string>();
    const [apellido, setApellido] = useState<string>();
    const [correo, setCorreo] = useState<string>();
    const [telefono, setTelefono] = useState<string>();
    const [salario, setSalario] = useState<string>();

    const [temp, setTemp] = useState<boolean>(false);

    const ruta = useRouter();

    useEffect(
        () => {
            data();
        }, []
    );

    const data = async () => {

        try {
            let response = await findEmployeId(id);
            let json;
            if (response?.status === 200) {
                json = await response.json();
                setId(json.data.id);
                setNombre(json.data.nombre);
                setApellido(json.data.apellido);
                setCorreo(json.data.correo);
                setTelefono(json.data.telefono);
                setSalario(json.data.salario);
                setTemp(true);
            }
            if (response?.status === 404) {
                alert("data incorrect");
                ruta.push('/');
            }
        } catch (error) {

        }
    }

    const updateEmployeServer = async (envent: any) => {
        envent.preventDefault();
        let data = JSON.stringify({
            id: ids,
            nombre,
            apellido,
            correo,
            telefono,
            salario
        });

        try {
            let response = await updateEmploye(data, id);
            if (response?.status === 200) {
                alert("Empleado actualizado correctamente");
                ruta.push("/");
            }
            if (response?.status === 400) {
                alert("Empleado no registrado en el sistema")
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
                        <Link href={"/"} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 uppercase px-8 p-2 mr-1 border-4 border-double bg-blue-500 shadow-lg shadow-blue-500/50">Inicio</Link>
                    </div>
                    <div className="pt-20">
                        <form onSubmit={updateEmployeServer}>
                            <div className="grid justify-items-stretch p-4 font-sans border-8 border-sky-500  sm:mx-96 bg-white">
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="id">ID</label>
                                    <div>
                                        <input onChange={(e) => setId(e.target.value)} type="number" placeholder="1" value={ids} disabled className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="nombre">NOMBRE</label>
                                    <div>
                                        <input onChange={(e) => setNombre(e.target.value)} autoFocus type="text" placeholder="Natalia" value={nombre} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="apellido">APELLIDO</label>
                                    <div>
                                        <input onChange={(e) => setApellido(e.target.value)} type="text" placeholder="Salazar" value={apellido} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="correo">CORREO</label>
                                    <div>
                                        <input onChange={(e) => setCorreo(e.target.value)} type="email" placeholder="natalia@gmail.com" value={correo} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="telefono">TELÉFONO</label>
                                    <div>
                                        <input onChange={(e) => setTelefono(e.target.value)} type="text" placeholder="+57 3184520045" value={telefono} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
                                </div>
                                <div className="justify-self-center m-2">
                                    <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="salario">SALARIO</label>
                                    <div>
                                        <input onChange={(e) => setSalario(e.target.value)} type="text" placeholder="5000000,45" value={salario} required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                                    </div>
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