#include "CTaxi.h"

CTaxi::CTaxi(size_t placeCount, ECarModel carModel)
    : CCarImpl(placeCount, carModel)
{
}

CTaxi::~CTaxi() = default;
