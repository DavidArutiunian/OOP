#pragma once
#include "ECarModel.h"
#include "IVehicle.h"

template <typename Passenger>
class ICar : public IVehicle<Passenger>
{
public:
    virtual ECarModel GetCarModel() const = 0;
};
