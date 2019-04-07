#include "CPoliceMan.h"
#include "CPoliceCar.h"
#include <iostream>
#include <cassert>
#include "CTaxi.h"
#include "CPerson.h"
#include "IRacer.h"
#include "CRacer.h"

int main()
{
    try
    {
        const std::shared_ptr<IPoliceMan> pJohnSmith(new CPoliceMan("John Smith", "Northwest Police Station"));
        const std::shared_ptr<IPoliceMan> pJimClark(new CPoliceMan("Jim Clark", "Southeast Police Station"));
        CPoliceCar policeCar(5, FORD);
        policeCar.AddPassenger(pJohnSmith);
        policeCar.AddPassenger(pJimClark);

        assert(policeCar.GetPassengerCount() == 2);
        assert(policeCar.GetPlaceCount() == 5);
        assert(policeCar.GetCarModel() == FORD);

        assert(policeCar.GetPassenger(0).GetName() == "John Smith");
        assert(policeCar.GetPassenger(0).GetDepartmentName() == "Northwest Police Station");

        std::cout
            << policeCar.GetPassenger(0).GetName()
            << "\t\t"
            << policeCar.GetPassenger(0).GetDepartmentName()
            << std::endl;

        assert(policeCar.GetPassenger(1).GetName() == "Jim Clark");
        assert(policeCar.GetPassenger(1).GetDepartmentName() == "Southeast Police Station");

        std::cout
            << policeCar.GetPassenger(1).GetName()
            << "\t\t"
            << policeCar.GetPassenger(1).GetDepartmentName()
            << std::endl;

        policeCar.RemovePassenger(1);

        const std::shared_ptr<IRacer> pMichaelSchumacher(new CRacer("Michael Schumacher", 322));
        const std::shared_ptr<IPerson> pRajaGandhi(new CPerson("Raja Gandhi"));
        CTaxi taxiCar(2, TOYOTA);
        taxiCar.AddPassenger(pRajaGandhi);
        taxiCar.AddPassenger(pMichaelSchumacher);

        assert(taxiCar.GetPassengerCount() == 2);
        assert(taxiCar.GetPlaceCount() == 2);
        assert(taxiCar.GetCarModel() == TOYOTA);

        taxiCar.RemovePassenger(0);
        taxiCar.AddPassenger(pJimClark);

        assert(taxiCar.GetPassenger(0).GetName() == "Michael Schumacher");
        assert(taxiCar.GetPassenger(1).GetName() == "Jim Clark");

        try
        {
            taxiCar.AddPassenger(pRajaGandhi);
        }
        catch (std::logic_error const& ex)
        {
            assert(ex.what() == "Vehicle is full!");
        }
    }
    catch (std::exception const& ex)
    {
        std::cerr << ex.what() << std::endl;
    }
}
