// methodName<> = (field: Field) => return React.FC<IResourceComponentsProps>
import {BaseRecord, IResourceComponentsProps} from "@pankod/refine-core";
import {Field} from "../../resources/Field";
import React from "react";

export type Decider = <T extends BaseRecord = BaseRecord>(field: Field[]) => React.FC<IResourceComponentsProps>