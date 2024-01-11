"use client"

import Link from "next/link";
import { useState } from "react";
import { createEmploye } from "../models/services/employeApi";
import { useRouter } from "next/navigation";

function Create() {

    const [nombre, setNombre] = useState<string>();
    const [apellido, setApellido] = useState<string>();
    const [correo, setCorreo] = useState<string>();
    const [telefono, setTelefono] = useState<string>();
    const [salario, setSalario] = useState<string>();

    const ruta = useRouter();

    const createEmployeServer = async (event: any) => {
        event.preventDefault();

        let data = JSON.stringify({
            nombre,
            apellido,
            correo,
            telefono,
            salario
        });
        try {

            let response = await createEmploye(data);
            if (response?.status === 200) {
                alert("Empleado registado correctamente")
                ruta.push("/");
            }
            if (response?.status === 400) {
                alert("Empleado registrado en el sistema")
            }
            if (response?.status === 428) {
                alert("Ingresar datos")
            }

        } catch (error) {

        }
    }

    return (
        <>
            <div className="flex justify-center p-4 mb-1 bg-blue-700 font-sans">
                <Link href={"/"} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 uppercase px-8 p-2 mr-1 border-4 border-double bg-blue-500 shadow-lg shadow-blue-500/50">Inicio</Link>
            </div>
            <div className="pt-20">
                <form onSubmit={createEmployeServer}>
                    <div className="grid justify-items-stretch p-4 font-sans border-8 border-sky-500  sm:mx-96 bg-white">
                        <div className="justify-self-center m-2">
                            <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="nombre">NOMBRE</label>
                            <div>
                                <input onChange={(e) => setNombre(e.target.value)} autoFocus type="text" placeholder="Natalia" required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                            </div>
                        </div>
                        <div className="justify-self-center m-2">
                            <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="apellido">APELLIDO</label>
                            <div>
                                <input onChange={(e) => setApellido(e.target.value)} type="text" placeholder="Salazar" required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                            </div>
                        </div>

                        <div className="justify-self-center m-2">
                            <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="telefono">TELÉFONO</label>
                            <div>
                                <input onChange={(e) => setTelefono(e.target.value)} type="text" placeholder="+57 3184520045" required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                            </div>
                        </div>
                        <div className="justify-self-center m-2">
                            <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="correo">CORREO</label>
                            <div>
                                <input onChange={(e) => setCorreo(e.target.value)} type="email" placeholder="natalia@gmail.com" required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                            </div>
                        </div>
                        <div className="justify-self-center m-2">
                            <label className="block text-sm font-medium leading-6 text-gray-900" htmlFor="salario">SALARIO</label>
                            <div>
                                <input onChange={(e) => setSalario(e.target.value)} type="text" placeholder="5000000,45" required className="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
                            </div>
                        </div>

                        <div className="justify-self-center m-4">
                            <button className="transition ease-in-out delay-150  hover:-translate-y-1 hover:scale-110 hover:bg-blue-500 duration-300 p-2  bg-cyan-500 shadow-lg shadow-cyan-500/50">CREAR</button>
                        </div>
                    </div>
                </form >
            </div>
        </>
    );

}

export default Create;