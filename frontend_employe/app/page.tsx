"use client"

import { useEffect, useState } from "react";
import { deleteEmploye, findEmployes } from "./models/services/employeApi";
import { Employe } from "./models/entity/employe";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { Supplier } from "./models/entity/supplier";
import { deleteSupplier, findSuppliers } from "./models/services/supplierApi";

export default function Home() {

  const ruta = useRouter();

  const [employe, setEmploye] = useState<Employe[]>([]);
  const [supplier, setSupplier] = useState<Supplier[]>([]);

  useEffect(
    () => {
      data();
    }, []
  );

  const data = async () => {

    try {
      let response = await findEmployes();
      let json;
      if (response?.status === 200) {
        json = await response.json();
        setEmploye(json);
      }

      let responseSu = await findSuppliers();
      let jsonSu
      if (responseSu?.status === 200) {
        jsonSu = await responseSu.json();
        setSupplier(jsonSu);
      }
    } catch (error) {

    }

  }
  const deleteEmployeServer = async (id: number) => {

    try {
      let response = await deleteEmploye(id);

      if (response?.status === 200) {
        alert("Empleado eliminado")
        window.location.reload();
      }

    } catch (error) {

    }
  }
  const deleteSupplietServer = async (id: number) => {

    try {
      let response = await deleteSupplier(id);

      if (response?.status === 200) {
        alert("Proveedor eliminado")
        window.location.reload();
      }

    } catch (error) {

    }
  }

  return (<>
    <div className="flex justify-center p-4 mb-1 bg-blue-700 font-sans ">
      <Link href={"/create"} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 uppercase px-8 p-2 mr-1 border-4 border-double bg-blue-500 shadow-lg shadow-blue-500/50">REGISTRAR EMPLEADO</Link>
      <Link href={"/createsup"} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 uppercase px-8 p-2 border-4 border-double shadow-2 bg-cyan-500 shadow-lg shadow-cyan-500/50">REGISTRAR PROVEEDOR</Link>
    </div>
    <div className="grid justify-items-center bg-gray-800 py-20">
      <table className=" border-separate border border-slate-500 border-spacing-2 table-auto">
        <thead>
          <tr>
            <th className="uppercase p-2 border border-slate-600 uppercase" colSpan={7}>Empleados</th>
          </tr>
          <tr>
            <th className="uppercase p-2 border border-slate-600">id</th>
            <th className="uppercase p-2 border border-slate-600">nombre</th>
            <th className="uppercase p-2 border border-slate-600">apellido</th>
            <th className="uppercase p-2 border border-slate-600">correo</th>
            <th className="uppercase p-2 border border-slate-600">teléfono</th>
            <th className="uppercase p-2 border border-slate-600">salario</th>
            <th className="uppercase p-2 border border-slate-600">Operaciones</th>
          </tr>
        </thead>
        <tbody>
          {employe.map((employe) => (
            <tr key={employe.id}>
               <td className="p-2 border border-slate-600">{employe.id}</td>
               <td className="p-2 border border-slate-600">{employe.nombre}</td>
               <td className="p-2 border border-slate-600">{employe.apellido}</td>
               <td className="p-2 border border-slate-600">{employe.correo}</td>
               <td className="p-2 border border-slate-600">{employe.telefono}</td>
               <td className="p-2 border border-slate-600">{employe.salario}</td>
               <td className="p-2 border border-slate-600">
                <button onClick={() => ruta.push('/create/' + employe.id)} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 mr-1 bg-cyan-500 shadow-lg shadow-cyan-500/50">Actualizar</button>
                <button onClick={() => deleteEmployeServer(employe.id)} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 bg-blue-500 shadow-lg shadow-blue-500/50">Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>

    <br />

    <div className="grid justify-items-center bg-gray-800 py-20">
      <table className=" border-separate border border-slate-500 border-spacing-2 table-auto ">
        <thead>
          <tr>
            <th className="uppercase p-2 border border-slate-600 uppercase" colSpan={8}>
              Proveedores
            </th>
          </tr>
          <tr >
            <th className="uppercase p-2 border border-slate-600">id</th>
            <th className="uppercase p-2 border border-slate-600">cedula</th>
            <th className="uppercase p-2 border border-slate-600">nombre</th>
            <th className="uppercase p-2 border border-slate-600">apellido</th>
            <th className="uppercase p-2 border border-slate-600">servicio</th>
            <th className="uppercase p-2 border border-slate-600">teléfono</th>
            <th className="uppercase p-2 border border-slate-600">contrato</th>
            <th className="uppercase p-2 border border-slate-600">Operaciones</th>
          </tr>
        </thead>
        <tbody>
          {supplier.map((supplier) => (
            <tr key={supplier.id}>
               <td className="p-2 border border-slate-600">{supplier.id}</td>
               <td className="p-2 border border-slate-600">{supplier.cedula}</td>
               <td className="p-2 border border-slate-600">{supplier.nombre}</td>
               <td className="p-2 border border-slate-600">{supplier.apellido}</td>
               <td className="p-2 border border-slate-600">{supplier.service}</td>
               <td className="p-2 border border-slate-600">{supplier.telefono}</td>
               <td className="p-2 border border-slate-600">{supplier.contrato}</td>
               <td className="p-2 border border-slate-600">
                <button onClick={() => ruta.push('/createsup/' + supplier.id)} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 mr-1 bg-cyan-500 shadow-lg shadow-cyan-500/50">Actualizar</button>
                <button onClick={() => deleteSupplietServer(supplier.id)} className="transition ease-in-out delay-150 bg-blue-500 hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 bg-blue-500 shadow-lg shadow-blue-500/50">Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  </>);
}
