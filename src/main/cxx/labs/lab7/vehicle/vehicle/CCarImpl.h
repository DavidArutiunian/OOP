#pragma once
#include "CVehicleImpl.h"
#include "ECarModel.h"

template <typename Base, typename Passenger>
class CCarImpl : public CVehicleImpl<Base, Passenger>
{
    const ECarModel m_carModel;

public:
    CCarImpl(size_t placeCount, ECarModel carModel)
        : CVehicleImpl<Base, Passenger>(placeCount)
        , m_carModel(carModel){};

    ~CCarImpl() = default;

    ECarModel GetCarModel() const override
    {
        return m_carModel;
    };
};
